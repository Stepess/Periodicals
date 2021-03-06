package model.dao.jdbc;

import model.exception.NotEnoughMoneyException;
import model.dao.SubscriptionDao;
import model.dao.mappers.SubscriptionDtoMapper;
import model.dao.mappers.SubscriptionMapper;
import model.entity.DTO.SubscriptionDto;
import model.entity.Payment;
import model.entity.Subscription;
import model.entity.User;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class JdbcSubscriptionDao implements SubscriptionDao {
    private final static Logger log = LogManager.getLogger(JdbcSubscriptionDao.class);
    private Connection connection;
    private ResourceManager manager;

    JdbcSubscriptionDao(Connection connection) {
        this.connection = connection;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(Subscription entity) {
        int result = 0;
        try (PreparedStatement setSubscriptionStatement = connection.prepareStatement(manager.getProperty("db.subscription.query.set"), Statement.RETURN_GENERATED_KEYS);
             PreparedStatement setPaymentStatement = connection.prepareStatement(manager.getProperty("db.payment.query.set"))
        ) {
            connection.setAutoCommit(false);
            setSubscriptionStatement.setString(1, entity.getState().toString().toLowerCase());
            setSubscriptionStatement.setDate(2, Date.valueOf(entity.getStartDate()));
            setSubscriptionStatement.setDate(3, Date.valueOf(entity.getEndDate()));
            setSubscriptionStatement.setInt(4, entity.getOwnerId());
            setSubscriptionStatement.setInt(5, entity.getPublication().getId());
            result += setSubscriptionStatement.executeUpdate();

            int subscriptionId;

            ResultSet resultSet = setSubscriptionStatement.getGeneratedKeys();
            if (resultSet.next()) {
                subscriptionId = resultSet.getInt(1);
            } else {
                throw new SQLException();
            }

            setPaymentStatement.setFloat(1, entity.getPayment().getBill().floatValue());
            setPaymentStatement.setInt(2, subscriptionId);
            setPaymentStatement.setInt(3, entity.getOwnerId());
            result += setPaymentStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error(ex);
                throw new RuntimeException(ex);
            }
            log.error(e);
            throw new RuntimeException(e);
        }
        return result > 1;
    }

    @Override
    public Subscription getById(int id) {
        SubscriptionMapper subscriptionMapper = new SubscriptionMapper();
        Subscription subscription = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.subscription.query.get.by.id"))) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subscription = subscriptionMapper.extractFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return subscription;
    }

    @Override
    public List<Subscription> getAll() {
        SubscriptionMapper subscriptionMapper = new SubscriptionMapper();
        List<Subscription> subscriptions = new LinkedList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.subscription.query.get.all"))
        ) {
            while (resultSet.next()) {
                subscriptions.add(subscriptionMapper.extractFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return subscriptions;
    }

    @Override
    public boolean update(Subscription entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        int result = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.subscription.query.delete"))) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return result > 0;
    }

    @Override
    public List<SubscriptionDto> getByUserLogin(String login, String state) {
        SubscriptionDtoMapper subscriptionMapper = new SubscriptionDtoMapper();
        List<SubscriptionDto> subscriptions = new LinkedList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.subscription.query.get.by.user"))) {
            statement.setString(1, login);
            statement.setString(2, state);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subscriptions.add(subscriptionMapper.extractFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return subscriptions;
    }

    @Override
    public void pay(User user, Subscription subscription) {
        try (
                PreparedStatement getUserMoneyStatement = connection.prepareStatement(manager.getProperty("db.user.query.get.money"));
                PreparedStatement getBillStatement = connection.prepareStatement(manager.getProperty("db.payment.query.get.bill"));
                PreparedStatement setPaidPubStatement = connection.prepareStatement(manager.getProperty("db.user.query.set.pub"));
                PreparedStatement updateSubscriptionStatement = connection.prepareStatement(manager.getProperty("db.user.query.set.sub.paid"));
                PreparedStatement updateAccountStatement = connection.prepareStatement(manager.getProperty("db.user.query.update.account"));
                PreparedStatement updatePaymentStatement = connection.prepareStatement(manager.getProperty("db.payment.query.update.date.time"))
        ) {
            connection.setAutoCommit(false);

            BigDecimal account;
            getUserMoneyStatement.setString(1, user.getLogin());
            try (ResultSet resultSet = getUserMoneyStatement.executeQuery()) {
                resultSet.next();
                account = resultSet.getBigDecimal("account");
            }

            BigDecimal bill;
            getBillStatement.setInt(1, subscription.getPayment().getId());
            try (ResultSet resultSet = getBillStatement.executeQuery()) {
                resultSet.next();
                bill = resultSet.getBigDecimal("bill");
            }

            if (account.compareTo(bill) < 0) {
                throw new NotEnoughMoneyException();
            }

            updateAccountStatement.setBigDecimal(1, account.subtract(bill));
            updateAccountStatement.setString(2, user.getLogin());
            updateAccountStatement.executeUpdate();

            updateSubscriptionStatement.setInt(1, subscription.getId());
            updateSubscriptionStatement.executeUpdate();

            setPaidPubStatement.setInt(1, user.getId());
            setPaidPubStatement.setInt(2, subscription.getPublication().getId());
            setPaidPubStatement.executeUpdate();

            updatePaymentStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            updatePaymentStatement.setInt(2, subscription.getPayment().getId());
            updatePaymentStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.error(e);
                throw new RuntimeException();
            }
            log.error(ex);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isUserSubscriptionUnique(String login, int publicationId) {
        SubscriptionMapper mapper = new SubscriptionMapper();
        Subscription subscription = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.subscription.query.check.unique"))) {
            statement.setString(1, login);
            statement.setInt(2, publicationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subscription = mapper.extractFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return subscription == null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException();
        }
    }
}

package model.dao.jdbc;

import controller.exception.NotEnoughMoney;
import model.dao.SubscriptionDao;
import model.dao.mappers.SubscriptionMapper;
import model.entity.Payment;
import model.entity.Subscription;
import model.entity.User;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MONTHS;

public class JdbcSubscriptionDao implements SubscriptionDao {
    private DataSource source;
    private ResourceManager manager;

    public JdbcSubscriptionDao(DataSource source) {
        this.source = source;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(Subscription entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
        ) {
            connection.setAutoCommit(false);
            try (
                    PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.subscription.query.set"));
                    //PreparedStatement paymentStatement = connection.prepareStatement(manager.getProperty("db.payment.query.set"))
                    ) {

                try{
                    statement.setString(1, entity.getState().toString().toLowerCase());
                    statement.setFloat(2, entity.getTotal().floatValue());
                    statement.setDate(3, Date.valueOf(entity.getStartDate()));
                    statement.setDate(4, Date.valueOf(entity.getEndDate()));
                    statement.setInt(5, entity.getOwnerId());
                    statement.setInt(6, entity.getPublication().getId());
                    //Payment payment = entity.getPayment();
                   /* if (payment != null) {
                        paymentStatement.setFloat(1, payment.getBill().floatValue());
                       // paymentStatement.setTimestamp(2, Timestamp.valueOf(payment.getPaymentDateTime()));
                        paymentStatement.setInt(2, entity.getPublication().getId());
                        paymentStatement.setInt(3, entity.getOwnerId());
                    }*/
                    //result +=paymentStatement.executeUpdate();
                    result = statement.executeUpdate();

                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public Subscription getById(int id) {
        SubscriptionMapper subscriptionMapper = new SubscriptionMapper();
        Subscription subscription = null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.subscription.query.get.by.id"))
        ) {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery()
            ) {
                while (resultSet.next()) {
                    subscription = subscriptionMapper.extractFromResultSet(resultSet);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return subscription;
    }

    @Override
    public List<Subscription> getAll() {
        SubscriptionMapper subscriptionMapper = new SubscriptionMapper();
        List<Subscription> subscriptions = new ArrayList<>();

        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.subscription.query.get.all"))
        ) {
            while (resultSet.next()) {
                subscriptions.add(subscriptionMapper.extractFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return subscriptions;
    }

    @Override
    public boolean update(Subscription entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
        ) {
            connection.setAutoCommit(false);
            try (
                    PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.subscription.query.update"));
                    PreparedStatement paymentStatement = connection.prepareStatement(manager.getProperty("db.payment.query.set"))
            ) {

                try{
                    /*statement.setString(1, entity.getState().toString().toLowerCase());
                    statement.setDate(2, Date.valueOf(entity.getStartDate()));
                    statement.setDate(3, Date.valueOf(entity.getEndDate()));
                    statement.setInt(4, entity.getOwnerId());
                    statement.setInt(5, entity.getPublication().getId());
                    statement.setInt(6, entity.getId());*/
                    statement.setString(1, entity.getState().toString().toLowerCase());
                    statement.setFloat(2, entity.getTotal().floatValue());
                    statement.setDate(3, Date.valueOf(entity.getStartDate()));
                    statement.setDate(4, Date.valueOf(entity.getEndDate()));
                    statement.setInt(5, entity.getOwnerId());
                    statement.setInt(6, entity.getPublication().getId());
                    Payment payment = entity.getPayment();
                    if (payment != null) {
                        paymentStatement.setFloat(1, payment.getBill().floatValue());
                        paymentStatement.setTimestamp(2, Timestamp.valueOf(payment.getPaymentDateTime()));
                        paymentStatement.setInt(3, entity.getPublication().getId());
                        paymentStatement.setInt(4, entity.getOwnerId());
                        paymentStatement.setInt(5, payment.getId());
                    }
                    result = statement.executeUpdate();

                    connection.commit();
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public boolean delete(Subscription entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.subscription.query.delete"))
        ) {
            statement.setInt(1,entity.getId());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public void close() {

    }

    @Override
    public List<Subscription> getByUserLogin(String login) {
        SubscriptionMapper subscriptionMapper = new SubscriptionMapper();
        List<Subscription> subscriptions = new ArrayList<>();
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.subscription.query.get.by.user"))
        ) {
            statement.setString(1, login);
            try (
                    ResultSet resultSet = statement.executeQuery()
            ) {
                while (resultSet.next()) {
                    subscriptions.add(subscriptionMapper.extractFromResultSet(resultSet));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return subscriptions;
    }

    @Override
    public boolean pay(User user,Subscription subscription) throws SQLException {
        boolean success;

        try(Connection connection = source.getConnection();){
            try (
                    PreparedStatement moneyStatement = connection.prepareStatement(manager.getProperty("db.user.query.get.money"));
                    PreparedStatement billStatement = connection.prepareStatement(manager.getProperty("db.user.query.get.bill"));
                    PreparedStatement setPaidPubStatement = connection.prepareStatement(manager.getProperty("db.user.query.set.pub"));
                    PreparedStatement updateSubStatement = connection.prepareStatement(manager.getProperty("db.user.query.set.sub.paid"));
                    PreparedStatement updateAccountStatement = connection.prepareStatement(manager.getProperty("db.user.query.update.account"));
                    PreparedStatement updatePayment = connection.prepareStatement(manager.getProperty("db.user.query.update.payment"));
                    PreparedStatement setPayment = connection.prepareStatement(manager.getProperty("db.payment.query.set"))
            )
            {


            /*moneyStatement.setString(1, user.getLogin());
            ResultSet set = moneyStatement.executeQuery();
            set.next();
            BigDecimal account = set.getBigDecimal("account");
            billStatement.setString(1, user.getLogin());*/
            /*set = moneyStatement.executeQuery();
            set.next();
            BigDecimal bill = set.getBigDecimal("bill");*/

                BigDecimal account = user.getAccount();

                //BigDecimal bill = subscription.getPayment().getBill();
                BigDecimal bill = subscription.getTotal();
                if (account.compareTo(bill) < 0) {
                    throw new NotEnoughMoney();
                }
                connection.setAutoCommit(false);
                updateAccountStatement.setBigDecimal(1,account.subtract(bill));
                updateAccountStatement.setString(2,user.getLogin());
                updateAccountStatement.executeUpdate();
                updateSubStatement.setInt(1, subscription.getId());
                updateSubStatement.executeUpdate();
                setPaidPubStatement.setInt(1, user.getId());
                setPaidPubStatement.setInt(2, subscription.getPublication().getId());
                setPaidPubStatement.executeUpdate();
                /*updatePayment.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                updatePayment.setInt(2, subscription.getPayment().getId());
                updatePayment.executeUpdate();*/
                setPayment.setFloat(1, bill.floatValue());
                setPayment.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
                setPayment.setInt(3, subscription.getId());
                setPayment.setInt(4, user.getId());
                setPayment.executeUpdate();
                connection.commit();




            } catch (SQLException ex) {
                connection.rollback();
                ex.printStackTrace();
                throw ex;
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;

        }


        return true;
    }

    @Override
    public boolean isUserSubscriptionUnique(String login, int publicationId) {


        SubscriptionMapper mapper = new SubscriptionMapper();
        Subscription subscription= null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.subscription.query.check.unique"))
        ) {
            statement.setString(1, login);
            statement.setInt(2, publicationId);
            try (
                    ResultSet resultSet = statement.executeQuery()

            ) {
                while (resultSet.next()) {
                    subscription = mapper.extractFromResultSet(resultSet);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return subscription==null;
    }
}

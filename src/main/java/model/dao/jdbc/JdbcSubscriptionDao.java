package model.dao.jdbc;

import model.dao.SubscriptionDao;
import model.dao.mappers.SubscriptionMapper;
import model.entity.Payment;
import model.entity.Subscription;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    PreparedStatement paymentStatement = connection.prepareStatement(manager.getProperty("db.payment.query.set"))
                    ) {

                try{
                    statement.setString(1, entity.getState().toString().toLowerCase());
                    statement.setDate(2, Date.valueOf(entity.getDateOfStart()));
                    statement.setDate(3, Date.valueOf(entity.getDateOfEnd()));
                    statement.setInt(4, entity.getOwnerId());
                    statement.setInt(5, entity.getPublication().getId());
                    Payment payment = entity.getPayment();
                    if (payment != null) {
                        paymentStatement.setFloat(1, payment.getBill().floatValue());
                        paymentStatement.setTimestamp(2, Timestamp.valueOf(payment.getDateTimeOfPayment()));
                        paymentStatement.setInt(3, entity.getPublication().getId());
                        paymentStatement.setInt(4, entity.getOwnerId());
                    }

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
                    statement.setString(1, entity.getState().toString().toLowerCase());
                    statement.setDate(2, Date.valueOf(entity.getDateOfStart()));
                    statement.setDate(3, Date.valueOf(entity.getDateOfEnd()));
                    statement.setInt(4, entity.getOwnerId());
                    statement.setInt(5, entity.getPublication().getId());
                    statement.setInt(6, entity.getId());
                    Payment payment = entity.getPayment();
                    if (payment != null) {
                        paymentStatement.setFloat(1, payment.getBill().floatValue());
                        paymentStatement.setTimestamp(2, Timestamp.valueOf(payment.getDateTimeOfPayment()));
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
}

package model.dao.jdbc;

import model.dao.PaymentDao;
import model.dao.mappers.PaymentMapper;
import model.entity.Payment;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPaymentDao implements PaymentDao {
    private DataSource source;
    private ResourceManager manager;

    public JdbcPaymentDao(DataSource source) {
        this.source = source;
        this.manager = new DataBaseManager();
    }

    @Override
    public List<Payment> getByUserLogin(String login) {
        PaymentMapper paymentMapper = new PaymentMapper();
        List<Payment> subscriptions = new ArrayList<>();
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.subscription.query.get.by.user"))
        ) {
            statement.setString(1, login);
            try (
                    ResultSet resultSet = statement.executeQuery()
            ) {
                while (resultSet.next()) {
                    subscriptions.add(paymentMapper.extractFromResultSet(resultSet));
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
    public boolean setInDb(Payment entity) {
        return false;
    }

    @Override
    public Payment getById(int id) {
        return null;
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public boolean update(Payment entity) {
        return false;
    }

    @Override
    public boolean delete(Payment entity) {
        return false;
    }

    @Override
    public void close() {

    }
}

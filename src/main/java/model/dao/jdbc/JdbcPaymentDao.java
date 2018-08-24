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
        /*int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.payment.query.set"))
        ) {


            statement.setBigDecimal(1,entity.getBill());
            statement.setString(2,entity.);
            statement.setString(3,entity.getEmail());
            statement.setString(4,entity.getRole().toString().toLowerCase());
            statement.setString(5,entity.getFirstName());
            statement.setString(6,entity.getNationalField("firstName"));//TODO guess how put right name
            statement.setString(7,entity.getLastName());
            statement.setString(8,entity.getNationalField("lastName"));//TODO guess how put right name
            statement.setString(9,entity.getAddress());
            statement.setString(10,entity.getNationalField("address"));//TODO guess how put right name
            statement.setFloat(11,entity.getAccount().floatValue());//TODO guess how handle money
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;*/
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

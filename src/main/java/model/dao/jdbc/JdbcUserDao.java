package model.dao.jdbc;

import model.dao.UserDao;
import model.dao.mappers.UserMapper;
import model.entity.User;
import model.exception.NotUniqueEmailException;
import model.exception.NotUniqueLoginException;

import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private final static Logger log = LogManager.getLogger(JdbcUserDao.class);
    private Connection connection;
    private ResourceManager manager;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(User entity) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.set"))) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getRole().toString().toLowerCase());
            statement.setString(5, entity.getFirstName());
            statement.setString(6, entity.getLastName());
            statement.setString(7, entity.getAddress());
            statement.setFloat(8, 0.0f);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return result > 0;
    }

    @Override
    public User getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        UserMapper userMapper = new UserMapper();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.user.query.get.all"))
        ) {
            while (resultSet.next()) {
                User user = userMapper.extractFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return users;
    }

    @Override
    public boolean isUserExist(String login) {
        User user = getByLogin(login);
        return user != null;
    }

    @Override
    public boolean checkUserPassword(String login, String password) {
        User user = getByLogin(login);
        return user.getPassword().equals(password);
    }


    @Override
    public User getByLogin(String login) {
        UserMapper userMapper = new UserMapper();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.get.by.login"))) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())//TODO change
                user = userMapper.extractFromResultSet(resultSet);

        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }

    @Override
    public void checkDataUnique(String login, String email) {
        try (
                PreparedStatement loginStatement = connection.prepareStatement(manager.getProperty("db.user.query.get.by.login"));
                PreparedStatement emailStatement = connection.prepareStatement(manager.getProperty("db.user.query.get.by.email"))
        ) {
            loginStatement.setString(1, login);
            ResultSet resultSet = loginStatement.executeQuery();
            if (resultSet.next()) {
                throw new NotUniqueLoginException();
            }

            emailStatement.setString(1, email);
            resultSet = emailStatement.executeQuery();
            if (resultSet.next()) {
                throw new NotUniqueEmailException();
            }

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public boolean update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.delete"))) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return result > 0;
    }

    @Override
    public boolean addMoneyToUser(String login, BigDecimal money) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.add.money"))) {
            statement.setString(2, login);
            statement.setFloat(1, money.floatValue());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return result > 0;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}

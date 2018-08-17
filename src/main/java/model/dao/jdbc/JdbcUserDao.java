package model.dao.jdbc;

import model.dao.UserDao;
import model.dao.mappers.UserMapper;
import model.entity.User;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO handle exception

public class JdbcUserDao implements UserDao {
    private DataSource source;
    private ResourceManager manager;

    public JdbcUserDao(DataSource source) {
        this.source = source;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(User entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.set"))
        ) {
            statement.setString(1,entity.getLogin());
            statement.setString(2,entity.getPassword());
            statement.setString(3,entity.getEmail());
            statement.setString(4,entity.getRole().toString().toLowerCase());
            statement.setString(5,entity.getFirstName());
            statement.setString(6,entity.getFirstName());//TODO guess how put right name
            statement.setString(7,entity.getLastName());
            statement.setString(8,entity.getLastName());//TODO guess how put right name
            statement.setString(9,entity.getAddress());
            statement.setFloat(10,entity.getAccount().floatValue());//TODO guess how handle money
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public User getById(int id) {
        UserMapper userMapper = new UserMapper();
        User user = null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.get.by.id"))
        ) {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery()

            ) {
                while (resultSet.next()) {
                    user = userMapper.extractFromResultSet(resultSet);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        UserMapper userMapper = new UserMapper();
        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.user.query.get.all"))
        ) {
            while (resultSet.next()) {
                User user = userMapper.extractFromResultSet(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }

    @Override
    public boolean isUserExist(String login, String password) {
        List<User> users = getAll();

        /*for (User user: users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                return true;
            }
        }*/

        return false;
    }

    @Override
    public User getByLogin(String login) {
        UserMapper userMapper = new UserMapper();
        User user = null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.get.by.login"))
        ) {
            statement.setString(1, login);
            try (
                    ResultSet resultSet = statement.executeQuery()

            ) {
                while (resultSet.next()) {
                    user = userMapper.extractFromResultSet(resultSet);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }

    @Override
    public boolean update(User entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.update"))
        ) {
            statement.setString(1,entity.getLogin());
            statement.setString(2,entity.getPassword());
            statement.setString(3,entity.getEmail());
            statement.setString(4,entity.getRole().toString().toLowerCase());
            statement.setString(5,entity.getFirstName());
            statement.setString(6,entity.getFirstName());//TODO guess how put right name
            statement.setString(7,entity.getLastName());
            statement.setString(8,entity.getLastName());//TODO guess how put right name
            statement.setString(9,entity.getAddress());
            statement.setFloat(10,entity.getAccount().floatValue());//TODO guess how handle money
            statement.setInt(11,entity.getId());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public boolean delete(User entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.user.query.delete"))
        ) {
            statement.setInt(1,entity.getId());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result<0;//TODO test is it right
    }

    @Override
    public void close()  {

    }
}

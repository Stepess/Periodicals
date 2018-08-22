package model.dao.jdbc;

import model.dao.UserDao;
import model.dao.mappers.UserMapper;
import model.entity.Payment;
import model.entity.User;
import model.service.LocaleHolder;
import model.service.resource.manager.DBFieldsManager;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO handle exception
//TODO maybe send to the dao DTO

public class JdbcUserDao implements UserDao {
    private DataSource source;
    private ResourceManager manager;
    //private LocaleHolder localeHolder;

    public JdbcUserDao(DataSource source) {
        this.source = source;
        this.manager = new DataBaseManager();
        //this.localeHolder = localeHolderl;
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

    //TODO todo
    @Override
    public boolean isUserExist(String login, String password) {
        User user = getByLogin(login);
        if (user == null) {
            return false;
        }

        String hashedPassword = null;
        try {
            hashedPassword = MD5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return hashedPassword.equals(user.getPassword());


    }

    private String MD5(String in) throws NoSuchAlgorithmException {


        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(in.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }


        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
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
            statement.setString(6,entity.getNationalField("firstName"));//TODO guess how put right name
            statement.setString(7,entity.getLastName());
            statement.setString(8,entity.getNationalField("lastName"));//TODO guess how put right name
            statement.setString(9,entity.getAddress());
            statement.setString(10,entity.getNationalField("address"));//TODO guess how put right name
            statement.setFloat(11,entity.getAccount().floatValue());//TODO guess how handle money
            statement.setInt(12,entity.getId());
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
        return result>0;
    }

    @Override
    public boolean pay(User user,Payment payment) {
        boolean success;



                try (Connection connection = source.getConnection();
                        PreparedStatement moneyStatement = connection.prepareStatement(manager.getProperty("db.user.query.get.money"));
                        PreparedStatement billStatement = connection.prepareStatement(manager.getProperty("db.user.query.get.bill"));
                        PreparedStatement setPaidPubStatement = connection.prepareStatement(manager.getProperty("db.user.query.set.pub"))
                        )
        {

            connection.setAutoCommit(false);
            moneyStatement.executeUpdate();



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void close()  {

    }
}

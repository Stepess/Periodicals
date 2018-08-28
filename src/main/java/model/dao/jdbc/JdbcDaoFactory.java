package model.dao.jdbc;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.SubscriptionDao;
import model.dao.UserDao;
import model.entity.Publication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getSource();
    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(getConnection());
    }

    @Override
    public PublicationDao createPublicationDao() {
        return new JdbcPublicationDao(getConnection());
    }

    @Override
    public SubscriptionDao createSubscriptionDao() {
        return new JdbcSubscriptionDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

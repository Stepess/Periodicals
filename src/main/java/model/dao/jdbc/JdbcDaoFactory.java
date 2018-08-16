package model.dao.jdbc;

import model.dao.DaoFactory;
import model.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(ConnectionPoolHolder.getSource());
    }
}

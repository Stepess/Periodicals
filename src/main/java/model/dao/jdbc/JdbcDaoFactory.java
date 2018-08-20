package model.dao.jdbc;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.UserDao;
import model.entity.Publication;

public class JdbcDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(ConnectionPoolHolder.getSource());
    }
    @Override
    public PublicationDao createPublicationDao() {
        return new JdbcPublicationDao(ConnectionPoolHolder.getSource());
    }
}

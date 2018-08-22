package model.dao;

import model.dao.jdbc.JdbcDaoFactory;
import model.entity.Publication;

public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract PublicationDao createPublicationDao();
    public abstract SubscriptionDao createSubscriptionDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JdbcDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

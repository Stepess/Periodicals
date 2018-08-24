package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.SubscriptionDao;
import model.dao.UserDao;
import model.dao.jdbc.JdbcUserDao;
import model.entity.Subscription;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionService {
    private SubscriptionDao dao;

    public SubscriptionService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        dao = daoFactory.createSubscriptionDao();
    }

    public List<Subscription> getAllUserSubscription(String login) {
        return dao.getByUserLogin(login);
    }

    public Subscription getById(int id) {
        return dao.getById(id);
    }

    public void pay(String login, Subscription subscription) throws SQLException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        dao.pay(daoFactory.createUserDao().getByLogin(login),
                subscription);
    }

    public void set(Subscription subscription) {
        dao.setInDb(subscription);
    }

    public boolean isUserSubscriptionUnique(String login, int publicationId) {
        return dao.isUserSubscriptionUnique(login, publicationId);
    }


}

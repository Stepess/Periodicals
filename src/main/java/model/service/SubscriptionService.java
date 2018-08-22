package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.SubscriptionDao;
import model.entity.Subscription;

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


}

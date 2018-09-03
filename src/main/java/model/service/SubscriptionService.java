package model.service;

import controller.exception.NotEnoughMoney;
import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.SubscriptionDao;
import model.dao.UserDao;
import model.dao.jdbc.JdbcUserDao;
import model.entity.DTO.SubscriptionDto;
import model.entity.Subscription;
import model.entity.User;
import model.service.resource.manager.PagePathManager;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public SubscriptionService() {
    }

    public List<SubscriptionDto> getAllUserSubscription(String login, String state) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            return dao.getByUserLogin(login, state);
        }
    }

    public Subscription getById(int id) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            return dao.getById(id);
        }
    }

    public void paySubscription(User user, Subscription subscription) {
        try (SubscriptionDao dao = daoFactory.createSubscriptionDao()) {
            if (user.getAccount().compareTo(subscription.getPayment().getBill()) < 0) {
                throw new NotEnoughMoney();
            }
            dao.pay(user, subscription);
        }
    }

    public void addSubscription(Subscription subscription) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            dao.setInDb(subscription);
        }
    }

    public boolean isUserSubscriptionUnique(String login, int publicationId) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            return dao.isUserSubscriptionUnique(login, publicationId);
        }
    }

    public boolean deleteUserSubscription(int id) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            return dao.delete(id);
        }
    }


}

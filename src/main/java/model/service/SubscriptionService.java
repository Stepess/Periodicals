package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.SubscriptionDao;
import model.dao.UserDao;
import model.dao.jdbc.JdbcUserDao;
import model.entity.DTO.SubscriptionDto;
import model.entity.Subscription;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public SubscriptionService() {
        /*DaoFactory daoFactory = DaoFactory.getInstance();
        try(SubscriptionDao dao1 = daoFactory.createSubscriptionDao()){
            dao = dao1;
        }*/
    }

    public List<SubscriptionDto> getAllUserSubscription(String login) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            return dao.getByUserLogin(login);
        }
    }

    public Subscription getById(int id) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            return dao.getById(id);
        }
    }

    public void pay(String login, Subscription subscription) throws SQLException {

        try(SubscriptionDao dao = daoFactory.createSubscriptionDao();
        UserDao userDao = daoFactory.createUserDao()){
            dao.pay(userDao.getByLogin(login), subscription);
        }
    }

    public void set(Subscription subscription) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            dao.setInDb(subscription);
        }
    }

    public boolean isUserSubscriptionUnique(String login, int publicationId) {
        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()){
            return dao.isUserSubscriptionUnique(login, publicationId);
        }
    }


}

package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.UserDao;
import model.entity.User;

import java.math.BigDecimal;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public UserService() {
        /*DaoFactory daoFactory = DaoFactory.getInstance();
        try(UserDao dao = daoFactory.createUserDao()){
            userDao = dao;
        }*/
    }

    public boolean checkLoginPassword(String login, String password) {
        try(UserDao dao = daoFactory.createUserDao()) {
            return dao.isUserExist(login, password);
        }
    }

    public User.RoleEnum getUserRole(String login) {
        try(UserDao dao = daoFactory.createUserDao()) {
            return dao.getByLogin(login).getRole();
        }
    }

    public int getUserId(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.getByLogin(login).getId();
        }
    }

    public void setInDb(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.setInDb(user);
        }
    }

    public void checkDataUnique(String login, String email) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.checkDataUnique(login, email);
        }
    }

    public User getUserByLogin(String login){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.getByLogin(login);
        }
    }

    public void replenishAccount(String login, BigDecimal sum) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.addMoneyToUser(login, sum);
        }
    }
}

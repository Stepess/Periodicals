package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.math.BigDecimal;

public class UserService {
    private UserDao userDao;

    public UserService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.createUserDao();
    }

    public boolean checkLoginPassword(String login, String password) {
        return userDao.isUserExist(login, password);
    }

    public User.RoleEnum getUserRole(String login) {
        return userDao.getByLogin(login).getRole();
    }

    public int getUserId(String login) {return userDao.getByLogin(login).getId();}

    public void setInDb(User user) {
        userDao.setInDb(user);
    }

    public void checkDataUnique(String login, String email) {
        userDao.checkDataUnique(login, email);
    }

    public User getUserByLogin(String login){return userDao.getByLogin(login);}

    public void replenishAccount(String login, BigDecimal sum) {userDao.addMoneyToUser(login, sum);}
}

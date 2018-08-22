package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

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
}

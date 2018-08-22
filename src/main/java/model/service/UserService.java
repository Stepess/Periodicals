package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import javax.servlet.http.HttpSession;

public class UserService {
    private UserDao userDao;

    public UserService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.createUserDao();
    }

    public boolean checkLoginPassword(String login, String password) {
        System.out.println(login + " " +password);
        return userDao.isUserExist(login, password);
    }

    /*public void markUserAsLoginned(Set<String> loginedUsers, String login) {
        if (loginedUsers == null) {
            loginedUsers = new TreeSet<>();
        } else {
            if ()
        }

    }
*/
    public void setUserInSession(HttpSession session, String login, User.RoleEnum role) {
        session.setAttribute("role", role.getValue());
        session.setAttribute("login", login);
    }

    public User.RoleEnum getUserRole(String login) {
        return userDao.getByLogin(login).getRole();
    }
}

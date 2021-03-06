package model.service;

import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.UserDao;
import model.entity.User;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public UserService() {
    }

    public boolean isUserExist(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.isUserExist(login);
        }
    }

    public boolean checkUserPassword(String login, String password) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.checkUserPassword(login, password);
        }
    }

    public User.RoleEnum getUserRole(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.getByLogin(login).getRole();
        }
    }

    public int getUserId(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.getByLogin(login).getId();
        }
    }

    public boolean registerUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.setInDb(user);
        }
    }

    public void checkDataUnique(String login, String email) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.checkDataUnique(login, email);
        }
    }

    public User getUserByLogin(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.getByLogin(login);
        }
    }

    public void replenishAccount(String login, BigDecimal sum) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.addMoneyToUser(login, sum);
        }
    }

    public String MD5(String in) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        md.update(in.getBytes());

        byte byteData[] = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

package model.dao;

import model.entity.User;

public interface UserDao extends GenericDao<User> {
    boolean isUserExist(String login, String password);
    User getByLogin(String login);
}

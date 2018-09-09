package model.dao;

import model.entity.User;

import java.math.BigDecimal;

public interface UserDao extends GenericDao<User> {
    boolean isUserExist(String login);
    boolean checkUserPassword(String login, String password);
    User getByLogin(String login);
    void checkDataUnique(String login, String email);
    boolean addMoneyToUser(String login, BigDecimal money);
}

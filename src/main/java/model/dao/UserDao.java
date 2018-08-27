package model.dao;

import model.entity.Payment;
import model.entity.User;

import java.math.BigDecimal;

public interface UserDao extends GenericDao<User> {
    boolean isUserExist(String login, String password);
    User getByLogin(String login);
    void checkDataUnique(String login, String email);
    boolean addMoneyToUser(String login, BigDecimal money);
}

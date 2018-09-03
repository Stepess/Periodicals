package model.dao;

import model.entity.DTO.SubscriptionDto;
import model.entity.Payment;
import model.entity.Subscription;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao extends GenericDao<Subscription> {
    List<SubscriptionDto> getByUserLogin(String login, String state);
    void pay(User user, Subscription subscription);
    boolean isUserSubscriptionUnique(String login, int publicationId);
}

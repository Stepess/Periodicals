package model.dao;

import model.entity.Subscription;
import model.entity.User;

import java.util.List;

public interface SubscriptionDao extends GenericDao<Subscription> {
    List<Subscription> getByUserLogin(String login);
}

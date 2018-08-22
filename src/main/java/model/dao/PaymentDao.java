package model.dao;

import model.entity.Payment;

import java.util.List;

public interface PaymentDao extends GenericDao<Payment> {
    List<Payment> getByUserLogin(String login);
}

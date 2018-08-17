package model.entity;

import model.service.builders.SubscriptionBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Subscription {
    private int id;
    private LocalDate dateOfStart;
    public enum StateEnum {
        ACTIVE, INACTIVE
    }
    private StateEnum state;
    private BigDecimal sum;
    private List<Publication> publicationList;
    private Payment payment;

    public Subscription(SubscriptionBuilder builder) {
        this.id = builder.getId();
        this.dateOfStart = builder.getDateOfStart();
        this.state = builder.getState();
        this.sum = builder.getSum();
        this.publicationList = builder.getPublicationList();
        this.payment = builder.getPayment();
    }
}

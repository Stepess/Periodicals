package model.entity;

import model.service.LocaleHolder;
import model.service.builders.SubscriptionBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Subscription {
    private int id;
    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;
    public enum StateEnum{
        PAID, UNPAID
    }
    private StateEnum state;
    private Publication publication;
    private Payment payment;
    private int ownerId;

    public Subscription(SubscriptionBuilder builder) {
        this.id = builder.getId();
        this.dateOfStart = builder.getDateOfStart();
        this.dateOfEnd = builder.getDateOfEnd();
        this.state = builder.getState();
        this.publication = builder.getPublication();
        this.payment = builder.getPayment();
        this.ownerId = builder.getOwnerId();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public LocalDate getDateOfEnd() {
        return dateOfEnd;
    }

    public StateEnum getState() {
        return state;
    }


    public Publication getPublication() {
        return publication;
    }

    public Payment getPayment() {
        return payment;
    }

    public int getOwnerId() {
        return ownerId;
    }
}

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
    public enum StateEnum {
        ACTIVE, INACTIVE
    }
    private StateEnum state;
    private BigDecimal sum;
    private List<BookedPublication> publicationList;
    private Payment payment;
    private int ownerId;

    public Subscription(SubscriptionBuilder builder) {
        this.id = builder.getId();
        this.dateOfStart = builder.getDateOfStart();
        //this.state = builder.getState();
        this.sum = builder.getSum();
        this.publicationList = builder.getPublicationList();
        this.payment = builder.getPayment();
        this.ownerId = builder.getOwnerId();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public List<BookedPublication> getPublicationList() {
        return publicationList;
    }

    public Payment getPayment() {
        return payment;
    }

    public int getOwnerId() {
        return ownerId;
    }
}

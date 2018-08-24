package model.entity;

import model.service.LocaleHolder;
import model.service.builders.SubscriptionBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Subscription {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    public enum StateEnum{
        PAID, UNPAID
    }
    private StateEnum state;
    private Publication publication;
    private Payment payment;
    private int ownerId;

    public Subscription(SubscriptionBuilder builder) {
        this.id = builder.getId();
        this.startDate = builder.getDateOfStart();
        this.endDate = builder.getDateOfEnd();
        this.state = builder.getState();
        this.publication = builder.getPublication();
        this.payment = builder.getPayment();
        this.ownerId = builder.getOwnerId();
    }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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

    public boolean isPaid() {
        return state == StateEnum.PAID;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", state=" + state +
                ", publication=" + publication +
                ", payment=" + payment +
                ", ownerId=" + ownerId +
                '}';
    }
}

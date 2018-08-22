package model.service.builders;

import model.entity.BookedPublication;
import model.entity.Payment;
import model.entity.Publication;
import model.entity.Subscription;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionBuilder {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Subscription.StateEnum state;
    private BigDecimal sum;//TODO maybe delete
    private Publication publication;
    private Payment payment;
    private int ownerId;

    public SubscriptionBuilder(int id) {
        this.id = id;
    }

    public SubscriptionBuilder buildStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public SubscriptionBuilder buildEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public SubscriptionBuilder buildState(Subscription.StateEnum state) {
        this.state = state;
        return this;
    }

    public SubscriptionBuilder buildSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public SubscriptionBuilder buildPublication(Publication publication) {//TODO check null?
        this.publication = publication;
        return this;
    }

    public SubscriptionBuilder buildPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

    public SubscriptionBuilder buildOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Subscription build(){
        return new Subscription(this);
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateOfStart() {
        return startDate;
    }

    public LocalDate getDateOfEnd() {
        return endDate;
    }

    public Subscription.StateEnum getState() {
        return state;
    }

    public BigDecimal getSum() {
        return sum;
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

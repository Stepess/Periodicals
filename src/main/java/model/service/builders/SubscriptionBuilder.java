package model.service.builders;

import model.entity.Payment;
import model.entity.Publication;
import model.entity.Subscription;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SubscriptionBuilder {
    private int id;
    private BigDecimal total;
    private LocalDate startDate;
    private LocalDate endDate;
    private Subscription.StateEnum state;
    private Publication publication;
    private Payment payment;
    private int ownerId;

    public SubscriptionBuilder() {
    }

    public SubscriptionBuilder(int id) {
        this.id = id;
    }

    public SubscriptionBuilder buildTotal(BigDecimal total) {
        this.total = total;
        return this;
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

    public SubscriptionBuilder buildPublication(Publication publication) {
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

    public Subscription build() {
        return new Subscription(this);
    }

    public int getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Subscription.StateEnum getState() {
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

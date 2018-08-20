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
    private LocalDate dateOfStart;
    //private Subscription.StateEnum state;
    private BigDecimal sum;
    private List<BookedPublication> publicationList;
    private Payment payment;
    private int ownerId;

    public SubscriptionBuilder(int id) {
        this.id = id;
    }

    public SubscriptionBuilder buildDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
        return this;
    }

    /*public SubscriptionBuilder buildState(Subscription.StateEnum state) {
        this.state = state;
        return this;
    }*/

    public SubscriptionBuilder buildSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public SubscriptionBuilder buildPublicationList(List<BookedPublication> publicationList) {//TODO check null?
        this.publicationList = new ArrayList<>(publicationList);
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
        return dateOfStart;
    }

    /*public Subscription.StateEnum getState() {
        return state;
    }*/

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

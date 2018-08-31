package model.entity;

import model.service.builders.PaymentBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int id;
    private BigDecimal bill;
    private LocalDateTime paymentDateTime;

    public Payment() {
    }

    public Payment(PaymentBuilder builder) {
        this.id = builder.getId();
        this.bill = builder.getBill();
        this.paymentDateTime = builder.getPaymentDateTime();
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", bill=" + bill +
                ", paymentDateTime=" + paymentDateTime +
                '}';
    }
}

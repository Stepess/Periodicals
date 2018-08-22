package model.entity;

import model.service.builders.PaymentBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int id;
    private BigDecimal bill;
    private LocalDateTime dateTimeOfPayment;

    public Payment(PaymentBuilder builder) {
        this.id = builder.getId();
        this.bill = builder.getBill();
        this.dateTimeOfPayment = builder.getDateTimeOfPayment();
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public LocalDateTime getDateTimeOfPayment() {
        return dateTimeOfPayment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", bill=" + bill +
                ", dateTimeOfPayment=" + dateTimeOfPayment +
                '}';
    }
}

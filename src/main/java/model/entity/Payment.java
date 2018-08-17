package model.entity;

import model.service.builders.PaymentBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int id;
    private BigDecimal bill;
    public enum StateEnum{
        PAID, UNPAID
    }
    private StateEnum state;
    private LocalDateTime dateTimeOfPayment;

    public Payment(PaymentBuilder builder) {
        this.id = builder.getId();
        this.bill = builder.getBill();
        this.state = builder.getState();
        this.dateTimeOfPayment = builder.getDateTimeOfPayment();
    }
}

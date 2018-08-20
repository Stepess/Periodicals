package model.service.builders;

import model.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentBuilder {
    private int id;
    private BigDecimal bill;
    private LocalDateTime dateTimeOfPayment;

    public PaymentBuilder(int id) {
        this.id = id;
    }

    public PaymentBuilder buildBill(BigDecimal bill) {
        this.bill = bill;//TODO guess gow better to copy
        return this;
    }

    public PaymentBuilder buildDateTimeOFPayment(LocalDateTime dateTimeOfPayment) {
        this.dateTimeOfPayment = dateTimeOfPayment;
        return this;
    }

    public Payment build() {
        return new Payment(this);
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
}



package model.service.builders;

import model.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentBuilder {
    private int id;
    private BigDecimal bill;
    private LocalDateTime paymentDateTime;

    public PaymentBuilder() {
    }

    public PaymentBuilder(int id) {
        this.id = id;
    }

    public PaymentBuilder buildBill(BigDecimal bill) {
        this.bill = bill;
        return this;
    }

    public PaymentBuilder buildPaymentDateTime(LocalDateTime paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
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

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }
}



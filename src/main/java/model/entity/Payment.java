package model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int id;
    private BigDecimal bill;
    public enum StateEnum{
        PAID, UNPAID
    }
    private StateEnum state;
    private LocalDateTime DateTimeOfPayment;
}

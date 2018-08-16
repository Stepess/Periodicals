package model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Subscription {
    private int id;
    private LocalDate dateOfStart;
    public enum StateEnum {
        ACTIVE, INACTIVE
    }
    private BigDecimal sum;
    private List<Publication> publicationList;
    private Payment payment;
}

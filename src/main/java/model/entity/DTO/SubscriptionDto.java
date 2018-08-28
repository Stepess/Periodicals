package model.entity.DTO;

import model.entity.Payment;
import model.entity.Publication;
import model.entity.Subscription;
import model.service.builders.PaymentBuilder;
import model.service.builders.SubscriptionBuilder;
import model.service.builders.SubscriptionDtoBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SubscriptionDto {
    private String ukrainian = "uk";

    private int id;
    private String titleEn;
    private String titleUa;
    private BigDecimal total;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime paymentDateTime;
    public enum StateEnum{
        PAID, UNPAID
    }
    private Subscription.StateEnum state;
    private int ownerId;
    private PublicationDto publicationDto;

    public SubscriptionDto(SubscriptionDtoBuilder builder) {
        this.id = builder.getId();
        this.titleEn = builder.getTitleEn();
        this.titleUa = builder.getTitleUa();
        this.total = builder.getTotal();
        this.startDate = builder.getStartDate();
        this.endDate = builder.getEndDate();
        this.state = builder.getState();
        this.paymentDateTime = builder.getPaymentDateTime();
        this.ownerId = builder.getOwnerId();
        this.publicationDto = builder.getPublicationDto();
    }

    public Subscription convertToInternationalizedEntity(Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", locale);
        System.out.println(startDate.format(formatter));
        return new SubscriptionBuilder(id)
                .buildPublication(publicationDto.convertToInternationalizedEntity(locale))
                .buildTotal(total)
                .buildPayment(
                        new PaymentBuilder()
                        .buildBill(total)
                        .buildPaymentDateTime(paymentDateTime)
                        .build())
                .buildStartDate(startDate)
                .buildEndDate(endDate)
                .buildState(state)
                .buildOwnerId(ownerId)
                .build();
    }


}

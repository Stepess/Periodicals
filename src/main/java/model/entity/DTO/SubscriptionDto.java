package model.entity.DTO;

import model.entity.Subscription;
import model.service.builders.PaymentBuilder;
import model.service.builders.PublicationBuilder;
import model.service.builders.SubscriptionBuilder;
import model.service.builders.SubscriptionDtoBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;


public class SubscriptionDto {
    private String ukrainian = "uk";

    private int id;
    private String titleEn;
    private String titleUa;
    private String genreEn;
    private String genreUa;
    private BigDecimal total;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime paymentDateTime;
    private Subscription.StateEnum state;
    private int ownerId;
    private int paymentId;
    private int publicationId;


    public SubscriptionDto(SubscriptionDtoBuilder builder) {
        this.id = builder.getId();
        this.titleEn = builder.getTitleEn();
        this.titleUa = builder.getTitleUa();
        this.genreEn = builder.getGenreEn();
        this.genreUa = builder.getGenreUa();
        this.total = builder.getTotal();
        this.startDate = builder.getStartDate();
        this.endDate = builder.getEndDate();
        this.state = builder.getState();
        this.paymentDateTime = builder.getPaymentDateTime();
        this.ownerId = builder.getOwnerId();
        this.paymentId = builder.getPaymentId();
        this.publicationId = builder.getPublicationId();
    }

    public Subscription convertToInternationalizedEntity(Locale locale) {
        return new SubscriptionBuilder(id)
                .buildPublication(
                        new PublicationBuilder(publicationId)
                                .buildTitle(locale.getLanguage().equals(ukrainian) ? titleUa : titleEn)
                                .buildGenre(locale.getLanguage().equals(ukrainian) ? genreUa : genreEn)
                                .build())
                .buildPayment(
                        new PaymentBuilder(paymentId)
                                .buildBill(total)
                                .buildPaymentDateTime(paymentDateTime)
                                .build())
                .buildStartDate(startDate)
                .buildEndDate(endDate)
                .buildState(state)
                .buildOwnerId(ownerId)
                .build();
    }

    @Override
    public String toString() {
        return "SubscriptionDto{" +
                "ukrainian='" + ukrainian + '\'' +
                ", id=" + id +
                ", titleEn='" + titleEn + '\'' +
                ", titleUa='" + titleUa + '\'' +
                ", genreEn='" + genreEn + '\'' +
                ", genreUa='" + genreUa + '\'' +
                ", total=" + total +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", paymentDateTime=" + paymentDateTime +
                ", state=" + state +
                ", ownerId=" + ownerId +
                ", paymentId=" + paymentId +
                ", publicationId=" + publicationId +
                '}';
    }
}

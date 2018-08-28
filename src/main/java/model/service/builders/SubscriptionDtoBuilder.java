package model.service.builders;

import model.entity.DTO.PublicationDto;
import model.entity.DTO.SubscriptionDto;
import model.entity.Payment;
import model.entity.Publication;
import model.entity.Subscription;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SubscriptionDtoBuilder {
    private int id;
    private String titleEn;
    private String titleUa;
    private BigDecimal total;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime paymentDateTime;
    private Subscription.StateEnum state;
    private int ownerId;
    private PublicationDto publicationDto;

    public SubscriptionDtoBuilder(){}

    public SubscriptionDtoBuilder(int id) {
        this.id = id;
    }

    public SubscriptionDtoBuilder buildTitleEn(String titleEn) {
        this.titleEn = titleEn;
        return this;
    }

    public SubscriptionDtoBuilder buildTitleUa(String titleUa) {
        this.titleUa = titleUa;
        return this;
    }

    public SubscriptionDtoBuilder buildTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public SubscriptionDtoBuilder buildStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public SubscriptionDtoBuilder buildEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public SubscriptionDtoBuilder buildState(Subscription.StateEnum state) {
        this.state = state;
        return this;
    }

    public SubscriptionDtoBuilder buildPaymentDateTime(LocalDateTime payment) {
        this.paymentDateTime = payment;
        return this;
    }

    public SubscriptionDtoBuilder buildOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public SubscriptionDtoBuilder buildPublicationDto(PublicationDto dto){
        this.publicationDto = dto;
        return this;
    }

    public SubscriptionDto build(){
        return new SubscriptionDto(this);
    }

    public int getId() {
        return id;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleUa() {
        return titleUa;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public Subscription.StateEnum getState() {
        return state;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public PublicationDto getPublicationDto() {
        return publicationDto;
    }
}

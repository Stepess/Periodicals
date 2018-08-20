package model.entity;

import model.service.builders.PublicationBuilder;
import model.service.builders.SubscriptionBuilder;

import java.time.LocalDate;

public class BookedPublication extends Publication {
    private LocalDate startTime;
    private LocalDate endTime;

    public BookedPublication(PublicationBuilder builder, LocalDate startTime, LocalDate endTime) {
        super(builder);
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

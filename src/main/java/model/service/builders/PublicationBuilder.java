package model.service.builders;

import model.entity.Publication;

import java.awt.*;
import java.math.BigDecimal;

public class PublicationBuilder {
    private int id;
    private String title;
    private String author;
    private String genre;
    private BigDecimal price;
    private String description;
    private Image image; //TODO figure out about work with image

    public PublicationBuilder(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public PublicationBuilder buildAuthor(String author) {
        this.author = author;
        return this;
    }

    public PublicationBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PublicationBuilder buildDescription(String description) {
        this.description = description;
        return this;
    }

    public PublicationBuilder buildImage(Image image) {
        this.image = image;
        return this;
    }

    public Publication build() {
        return new Publication(this);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }
}

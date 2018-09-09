package model.entity;

import model.service.builders.PublicationBuilder;

import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Publication {
    private int id;
    private String title;
    private String author;
    private String genre;
    private BigDecimal price;
    private String description;

    public Publication(String title) {
        this.title = title;
    }

    public Publication(PublicationBuilder builder) {
        this.id = builder.getId();
        this.title = builder.getTitle();
        this.author = builder.getAuthor();
        this.genre = builder.getGenre();
        this.price = builder.getPrice();
        this.description = builder.getDescription();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, price, description);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}

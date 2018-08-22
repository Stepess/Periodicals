package model.entity;

import com.mysql.cj.jdbc.Blob;
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
    private Image image; //TODO figure out about work with image
    //private Blob blob;
    private Map<String, String> nationalFields;

    public Publication(PublicationBuilder builder) {
        this.id = builder.getId();
        this.title = builder.getTitle();
        this.author = builder.getAuthor();
        this.genre = builder.getGenre();
        this.price = builder.getPrice();
        this.description = builder.getDescription();
        this.image = builder.getImage();
        nationalFields = new HashMap<>();
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
                Objects.equals(description, that.description) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, price, description, image);
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
                ", image=" + image +
                '}';
    }

    public String getNationalField(String key) {
        return nationalFields.get(key);
    }

    public void setNationalField(String key, String field) {
        this.nationalFields.put(key, field);
    }
}

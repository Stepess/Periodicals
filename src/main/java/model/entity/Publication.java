package model.entity;

import com.mysql.cj.jdbc.Blob;
import model.service.builders.PublicationBuilder;

import java.awt.*;
import java.math.BigDecimal;

public class Publication {
    private int id;
    private String title;
    private String genre;
    private BigDecimal price;
    private String description;
    private Image image; //TODO figure out about work with image
    //private Blob blob;

    public Publication(PublicationBuilder builder) {
        this.id = builder.getId();
        this.title = builder.getTitle();
        this.genre = builder.getGenre();
        this.price = builder.getPrice();
        this.description = builder.getDescription();
        this.image = builder.getImage();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

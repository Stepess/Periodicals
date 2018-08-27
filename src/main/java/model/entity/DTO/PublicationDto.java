package model.entity.DTO;

import model.entity.Publication;
import model.service.builders.PublicationBuilder;
import model.service.builders.PublicationDtoBuilder;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Locale;


public class PublicationDto {
    private String ukrainian = "uk";

    private int id;
    private String titleEn;
    private String titleUa;
    private String author;
    private String genreEn;
    private String genreUa;
    private BigDecimal price;
    private String descriptionEn;
    private String descriptionUa;
    private Image image; //TODO figure out about work with image

    public PublicationDto(PublicationDtoBuilder builder) {
        this.id = builder.getId();
        this.titleEn = builder.getTitleEn();
        this.titleUa = builder.getTitleUa();
        this.author = builder.getAuthor();
        this.genreEn = builder.getGenreEn();
        this.genreUa = builder.getGenreUa();
        this.price = builder.getPrice();
        this.descriptionEn = builder.getDescriptionEn();
        this.descriptionUa = builder.getDescriptionUa();
    }

    public Publication convertToInternationalizedEntity(Locale locale) {
        return new PublicationBuilder(id)
                .buildTitle(locale.getLanguage().equals(ukrainian) ? titleUa : titleEn)
                .buildGenre(locale.getLanguage().equals(ukrainian) ? genreUa : genreEn)
                .buildAuthor(author)
                .buildPrice(price)
                .buildDescription(locale.getLanguage().equals(ukrainian) ? descriptionUa : descriptionEn)
                .build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleUa() {
        return titleUa;
    }

    public void setTitleUa(String titleUa) {
        this.titleUa = titleUa;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenreEn() {
        return genreEn;
    }

    public void setGenreEn(String genreEn) {
        this.genreEn = genreEn;
    }

    public String getGenreUa() {
        return genreUa;
    }

    public void setGenreUa(String genreUa) {
        this.genreUa = genreUa;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PublicationDto{" +
                "id=" + id +
                ", titleEn='" + titleEn + '\'' +
                ", titleUa='" + titleUa + '\'' +
                ", author='" + author + '\'' +
                ", genreEn='" + genreEn + '\'' +
                ", genreUa='" + genreUa + '\'' +
                ", price=" + price +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", descriptionUa='" + descriptionUa + '\'' +
                ", image=" + image +
                '}';
    }
}

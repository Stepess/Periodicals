package model.service.builders;

import model.entity.DTO.PublicationDto;

import java.math.BigDecimal;

public class PublicationDtoBuilder {
    private int id;
    private String titleEn;
    private String titleUa;
    private String author;
    private String genreEn;
    private String genreUa;
    private BigDecimal price;
    private String descriptionEn;
    private String descriptionUa;

    public PublicationDtoBuilder() {}

    public PublicationDtoBuilder(int id) {
        this.id = id;
    }

    public PublicationDtoBuilder buildTitleEn(String titleEn) {
        this.titleEn = titleEn;
        return this;
    }

    public PublicationDtoBuilder buildTitleUa(String titleUa) {
        this.titleUa = titleUa;
        return this;
    }

    public PublicationDtoBuilder buildGenreEn(String genreEn) {
        this.genreEn = genreEn;
        return this;
    }

    public PublicationDtoBuilder buildGenreUa(String genreUa) {
        this.genreUa = genreUa;
        return this;
    }

    public PublicationDtoBuilder buildAuthor(String author) {
        this.author = author;
        return this;
    }

    public PublicationDtoBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PublicationDtoBuilder buildDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
        return this;
    }

    public PublicationDtoBuilder buildDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
        return this;
    }



    public PublicationDto build() {
        return new PublicationDto(this);
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

    public String getAuthor() {
        return author;
    }

    public String getGenreEn() {
        return genreEn;
    }

    public String getGenreUa() {
        return genreUa;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }
}

package model.dao.mappers;

import model.entity.DTO.PublicationDTO;
import model.service.builders.PublicationDtoBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicationDTOMapper implements ObjectMapper<PublicationDTO> {

    @Override
    public PublicationDTO extractFromResultSet(ResultSet resultSet) throws SQLException {
        PublicationDtoBuilder builder = new PublicationDtoBuilder(resultSet.getInt("publication.id"))
                .buildTitleEn(resultSet.getString("title_en"))
                .buildTitleUa(resultSet.getString("title_ua"))
                .buildAuthor(resultSet.getString("author"))
                .buildGenreEn(resultSet.getString("genre_en"))
                .buildGenreUa(resultSet.getString("genre_ua"))
                .buildPrice(resultSet.getBigDecimal("price"))
                //.getImage(resultSet.getBlob("image"));//TODO
                .buildDescriptionEn( resultSet.getString("description_en"))
                .buildDescriptionUa( resultSet.getString("description_ua"));
        return builder.build();
    }
}

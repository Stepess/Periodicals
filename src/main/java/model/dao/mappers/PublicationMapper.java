package model.dao.mappers;

import model.entity.Publication;
import model.service.builders.PublicationBuilder;
import model.service.resource.manager.DBFieldsManager;
import model.service.resource.manager.ResourceManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;


public class PublicationMapper implements ObjectMapper<Publication> {
    @Override
    public Publication extractFromResultSet(ResultSet resultSet) throws SQLException {
        PublicationBuilder builder = new PublicationBuilder(resultSet.getInt("publication.id"))
                .buildTitle(resultSet.getString("title_en"))
                .buildGenre(resultSet.getString("genre_en"))
                .buildAuthor(resultSet.getString("author"))
                .buildPrice(resultSet.getBigDecimal("price"))
                .buildDescription(resultSet.getString( ("description_en")));
        return builder.build();
    }

    public Publication makeUnique(Map<Integer, Publication> cache, Publication publication) {
        cache.putIfAbsent(publication.getId(), publication);
        return cache.get(publication.getId());
    }
}

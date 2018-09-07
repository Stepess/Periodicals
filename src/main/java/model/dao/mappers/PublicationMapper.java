package model.dao.mappers;

import model.entity.Publication;
import model.service.builders.PublicationBuilder;
import model.service.resource.manager.DBFieldsManager;
import model.service.resource.manager.ResourceManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

//TODO delete
public class PublicationMapper implements ObjectMapper<Publication> {
    @Override
    public Publication extractFromResultSet(ResultSet resultSet) throws SQLException {
        ResourceManager manager = new DBFieldsManager(new Locale("uk", "UA"));//TODO switch language
        /*PublicationBuilder builder = new PublicationBuilder(resultSet.getInt("id"),
                resultSet.getString(manager.getProperty("db.publication.title")) == null ?
                        resultSet.getString("title_en") :
                        resultSet.getString(manager.getProperty("db.publication.title")),
                resultSet.getString(manager.getProperty("db.publication.genre")) == null ?
                        resultSet.getString("genre_en") :
                        resultSet.getString(manager.getProperty("db.publication.genre")))*/



        PublicationBuilder builder = new PublicationBuilder(resultSet.getInt("publication.id"))
                .buildTitle(resultSet.getString(manager.getProperty("db.publication.title")) == null ?
                        resultSet.getString("title_en") :
                        resultSet.getString(manager.getProperty("db.publication.title")))
                .buildGenre(resultSet.getString(manager.getProperty("db.publication.genre")) == null ?
                        resultSet.getString("genre_en") :
                        resultSet.getString(manager.getProperty("db.publication.genre")))
                .buildAuthor(resultSet.getString("author"))
                .buildPrice(resultSet.getBigDecimal("price"))
                //.getImage(resultSet.getBlob("image"));//TODO
                .buildDescription(resultSet.getString( manager.getProperty("db.publication.description")) == null ?
                        resultSet.getString("description_en") :
                        resultSet.getString(manager.getProperty("db.publication.description")));
        return builder.build();
    }

    public Publication makeUnique(Map<Integer, Publication> cache, Publication publication) {
        cache.putIfAbsent(publication.getId(), publication);
        return cache.get(publication.getId());
    }
}

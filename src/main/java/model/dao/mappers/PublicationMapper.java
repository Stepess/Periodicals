package model.dao.mappers;

import model.entity.Publication;
import model.service.builders.PublicationBuilder;
import model.service.resource.manager.DBFieldsManager;
import model.service.resource.manager.ResourceManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicationMapper implements ObjectMapper<Publication> {
    @Override
    public Publication extractFromResultSet(ResultSet resultSet) throws SQLException {
        ResourceManager manager = new DBFieldsManager();//TODO switch language
        PublicationBuilder builder = new PublicationBuilder(resultSet.getInt("id"),
                resultSet.getString(manager.getProperty("db.publication.title")),
                resultSet.getString(manager.getProperty("db.publication.genre")))
                .buildPrice(resultSet.getBigDecimal("price"))
                //.getImage(resultSet.getBlob("image"));
                .buildDescription(resultSet.getString(manager.getProperty("db.publication.description")));
        return builder.build();
    }
}

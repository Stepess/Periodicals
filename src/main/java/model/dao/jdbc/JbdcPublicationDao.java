package model.dao.jdbc;

import model.dao.PublicationDao;
import model.dao.mappers.PublicationMapper;
import model.entity.Publication;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JbdcPublicationDao implements PublicationDao {
    private DataSource source;
    private ResourceManager manager;

    public JbdcPublicationDao(DataSource source) {
        this.source = source;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(Publication entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.set"))
        ) {
            //TODO write after final version of schema
            statement.setString(1,entity.getTitle());
            statement.setString(2,entity.getTitle());
            statement.setString(3,entity.getGenre());
            statement.setString(4,entity.getGenre());
            statement.setString(5,entity.getPrice().toString());//TODO guess what to do with money
            statement.setString(6,entity.getDescription());//TODO guess how put right name
            statement.setString(7,entity.getDescription());
            //statement.setString(8,entity.getImage());//TODO guess how put right name
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public Publication getById(int id) {
        PublicationMapper mapper = new PublicationMapper();
        Publication publication = null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.get.by.id"))
        ) {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery()

            ) {
                while (resultSet.next()) {
                    publication = mapper.extractFromResultSet(resultSet);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return publication;
    }

    @Override
    public List<Publication> getAll() {
        List<Publication> publications = new ArrayList<>();
        PublicationMapper mapper = new PublicationMapper();
        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.publication.query.get.all"))
        ) {
            while (resultSet.next()) {
                Publication publication = mapper.extractFromResultSet(resultSet);
                publications.add(publication);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return publications;
    }

    @Override
    public boolean update(Publication entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.update"))
        ) {
            statement.setString(1,entity.getTitle());
            statement.setString(2,entity.getTitle());
            statement.setString(3,entity.getGenre());
            statement.setString(4,entity.getGenre());
            statement.setString(5,entity.getPrice().toString());//TODO guess what to do with money
            statement.setString(6,entity.getDescription());//TODO guess how put right name
            statement.setString(7,entity.getDescription());
            //statement.setString(8,entity.getImage());//TODO guess how put right name
            statement.setInt(8,entity.getId());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public boolean delete(Publication entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.delete"))
        ) {
            statement.setInt(1,entity.getId());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result<0;//TODO test is it right
    }

    @Override
    public void close() {

    }
}

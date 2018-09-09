package model.dao.jdbc;

import model.dao.PublicationDao;
import model.dao.mappers.PublicationDTOMapper;
import model.dao.mappers.PublicationMapper;
import model.dao.mappers.UserMapper;
import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.entity.User;
import model.exception.NotUniqueTitleEnException;
import model.exception.NotUniqueTitleUaException;
import model.exception.NothingFoundException;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcPublicationDao implements PublicationDao {
    private final static Logger log = LogManager.getLogger(JdbcSubscriptionDao.class);

    private Connection connection;
    private ResourceManager manager;

    public JdbcPublicationDao(Connection connection) {
        this.connection = connection;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(PublicationDto entity) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.set"))) {
            statement.setString(1, entity.getTitleEn());
            statement.setString(2, entity.getTitleUa());
            statement.setString(3, entity.getAuthor());
            statement.setString(4, entity.getGenreEn());
            statement.setString(5, entity.getGenreUa());
            statement.setFloat(6, entity.getPrice().floatValue());
            statement.setString(7, entity.getDescriptionEn());
            statement.setString(8, entity.getDescriptionUa());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return result > 0;
    }

    @Override
    public PublicationDto getById(int id) {
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        PublicationDto publicationDto = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.get.by.id"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publicationDto = mapper.extractFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            log.error(e);
        }
        return publicationDto;
    }

    //TODO do I need?
    @Override
    public List<PublicationDto> getAll() {
        List<PublicationDto> publications = new ArrayList<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.publication.query.get.all"))
        ) {
            while (resultSet.next()) {
                PublicationDto publicationDto = mapper.extractFromResultSet(resultSet);
                publications.add(publicationDto);
            }
        } catch (SQLException e) {
            log.error(e);

        }
        return publications;
    }

    //TODO think about changing title

    @Override
    public boolean update(PublicationDto entity) {
        int result = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.update"))) {
            statement.setString(1, entity.getTitleEn());
            statement.setString(2, entity.getTitleUa());
            statement.setString(3, entity.getAuthor());
            statement.setString(4, entity.getGenreEn());
            statement.setString(5, entity.getGenreUa());
            statement.setFloat(6, entity.getPrice().floatValue());
            statement.setString(7, entity.getDescriptionEn());
            statement.setString(8, entity.getDescriptionUa());
            statement.setInt(10, entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return result > 0;
    }

    @Override
    public boolean delete(int id) {
        int result = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.delete"))) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
        return result > 0;
    }

    public Map<PublicationDto, Integer> getStatistics() {
        Map<PublicationDto, Integer> result = new HashMap<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.statistics"))) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.put(mapper.extractFromResultSet(resultSet), resultSet.getInt("count(id)"));
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return result;
    }

    public List<User> getReport(int id) {
        List<User> report = new ArrayList<>();
        UserMapper mapper = new UserMapper();
        User user;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.get.report"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = mapper.extractFromResultSet(resultSet);
                report.add(user);
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return report;
    }

    @Override
    public void checkDataUnique(String titleEn, String titleUa) {
        try (
                PreparedStatement titleEnStatement =
                        connection.prepareStatement(manager.getProperty("db.publication.query.get.title.en"));
                PreparedStatement titleUaStatement =
                        connection.prepareStatement(manager.getProperty("db.publication.query.get.title.ua"))
        ) {
            titleEnStatement.setString(1, titleEn);
            ResultSet resultSet = titleEnStatement.executeQuery();
            if (resultSet.next()) {
                throw new NotUniqueTitleEnException();
            }

            titleUaStatement.setString(1, titleUa);
            resultSet = titleUaStatement.executeQuery();
            if (resultSet.next()) {
                throw new NotUniqueTitleUaException();
            }

        } catch (SQLException e) {
            log.error(e);
        }
    }

    //TODO do I need?
    @Override
    public List<PublicationDto> search(Map<String, String> searchParameters) {
        List<PublicationDto> publications = new ArrayList<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.search"))) {
            statement.setString(1, "%" + searchParameters.getOrDefault("title", "") + "%");
            statement.setString(2, "%" + searchParameters.getOrDefault("title", "") + "%");
            statement.setString(3, "%" + searchParameters.getOrDefault("genre", "") + "%");
            statement.setString(4, "%" + searchParameters.getOrDefault("genre", "") + "%");
            statement.setFloat(5, Float.parseFloat(searchParameters
                    .getOrDefault("leftPriceBoundary", "0.0")));
            statement.setFloat(6, Float.parseFloat(searchParameters
                    .getOrDefault("rightPriceBoundary", "10000.0")));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PublicationDto publicationDto = mapper.extractFromResultSet(resultSet);
                publications.add(publicationDto);
            }

            if (publications.isEmpty()) {
                throw new NothingFoundException();
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return publications;
    }


    @Override
    public int getNumberOfPublications() {
        int number = 0;
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.publication.query.get.count"))
        ) {
            resultSet.next();
            number = resultSet.getInt("count(id)");
        } catch (SQLException e) {
            log.error(e);
        }
        return number;
    }

    @Override
    public List<PublicationDto> getPaginatedList(int start, int recordsPerPage) {
        List<PublicationDto> publications = new ArrayList<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.get.limit"))) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PublicationDto publicationDto = mapper.extractFromResultSet(resultSet);
                publications.add(publicationDto);
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return publications;
    }

    @Override
    public int getNumberOfSearchedPublications(Map<String, String> searchParameters) {
        int number = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.get.search.count"))) {
            statement.setString(1, "%" + searchParameters.getOrDefault("title", "") + "%");
            statement.setString(2, "%" + searchParameters.getOrDefault("title", "") + "%");
            statement.setString(3, "%" + searchParameters.getOrDefault("genre", "") + "%");
            statement.setString(4, "%" + searchParameters.getOrDefault("genre", "") + "%");
            statement.setFloat(5, Float.parseFloat(searchParameters
                    .getOrDefault("leftPriceBoundary", "0.0")));
            statement.setFloat(6, Float.parseFloat(searchParameters
                    .getOrDefault("rightPriceBoundary", "10000.0")));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            number = resultSet.getInt("count(id)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (number == 0) {
            throw new NothingFoundException();
        }
        return number;
    }

    @Override
    public List<PublicationDto> getPaginatedSearchList(Map<String, String> searchParameters, int start, int recordsPerPage) {
        List<PublicationDto> publications = new ArrayList<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (PreparedStatement statement =
                     connection.prepareStatement(manager.getProperty("db.publication.query.search.limit"))) {
            statement.setString(1, "%" + searchParameters.getOrDefault("title", "") + "%");
            statement.setString(2, "%" + searchParameters.getOrDefault("title", "") + "%");
            statement.setString(3, "%" + searchParameters.getOrDefault("genre", "") + "%");
            statement.setString(4, "%" + searchParameters.getOrDefault("genre", "") + "%");
            statement.setFloat(5, Float.parseFloat(searchParameters
                    .getOrDefault("leftPriceBoundary", "0.0")));
            statement.setFloat(6, Float.parseFloat(searchParameters
                    .getOrDefault("rightPriceBoundary", "10000.0")));
            statement.setInt(7, start);
            statement.setInt(8, recordsPerPage);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PublicationDto publicationDto = mapper.extractFromResultSet(resultSet);
                publications.add(publicationDto);
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return publications;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException();
        }
    }
}

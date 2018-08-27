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

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcPublicationDao implements PublicationDao {
    private DataSource source;
    private ResourceManager manager;

    public JdbcPublicationDao(DataSource source) {
        this.source = source;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(PublicationDto entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.set"))
        ) {
            //TODO write after final version of schema



            /*tatement.setString(1,getEnString(entity.getTitle()));
            statement.setString(2,getUaString(entity.getTitle()));
            statement.setString(3,entity.getAuthor());
            statement.setString(4,getEnString(entity.getGenre()));
            statement.setString(5,getUaString(entity.getGenre()));
            statement.setFloat(6,entity.getPrice().floatValue());//TODO guess what to do with money
            statement.setString(7,getEnString(entity.getDescription()));//TODO guess how put right name
            statement.setString(8,getUaString(entity.getDescription()));
            statement.setBlob(9, connection.createBlob());//TODO guess how put right name
            result = statement.executeUpdate();*/

            statement.setString(1,entity.getTitleEn());
            statement.setString(2,entity.getTitleUa());
            statement.setString(3,entity.getAuthor());
            statement.setString(4,entity.getGenreEn());
            statement.setString(5,entity.getGenreUa());
            statement.setFloat(6,entity.getPrice().floatValue());
            statement.setString(7,entity.getDescriptionEn());
            statement.setString(8,entity.getDescriptionUa());
            statement.setBlob(9, connection.createBlob());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    private String getEnString(String string) {
        int index = string.indexOf("/en");
        return string.substring(0,index);
    }

    private String getUaString(String string) {
        int index = string.indexOf("/en");
        return string.substring(index+3, string.length()-3);
    }


    @Override
    public PublicationDto getById(int id) {
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        PublicationDto publicationDto = null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.get.by.id"))
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    publicationDto = mapper.extractFromResultSet(resultSet);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return publicationDto;
    }

    @Override
    public List<PublicationDto> getAll() {
        List<PublicationDto> publications = new ArrayList<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.publication.query.get.all"))
        ) {
            while (resultSet.next()) {
                PublicationDto publicationDto = mapper.extractFromResultSet(resultSet);
                publications.add(publicationDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return publications;
    }

    @Override
    public boolean update(PublicationDto entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.update"))
        ) {
            statement.setString(1,entity.getTitleEn());
            statement.setString(2,entity.getTitleUa());
            statement.setString(3,entity.getAuthor());
            statement.setString(4,entity.getGenreEn());
            statement.setString(5,entity.getGenreUa());
            statement.setFloat(6,entity.getPrice().floatValue());
            statement.setString(7,entity.getDescriptionEn());
            statement.setString(8,entity.getDescriptionUa());
            statement.setBlob(9, connection.createBlob());
            statement.setInt(10,entity.getId());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public boolean delete(int id) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.delete"))
        ) {
            statement.setInt(1,id);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    public Map<String, Integer> getStatistics() {
        Map<String, Integer> result = new HashMap<>();

        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.statistics"))
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.put(resultSet.getString("title_en"), resultSet.getInt("count(title_en)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getReport(int id) {
        List<User> report = new ArrayList<>();
        User user = null;
        UserMapper mapper = new UserMapper();

        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.get.report"))
        ) {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery()

            ) {
                while (resultSet.next()) {
                    user = mapper.extractFromResultSet(resultSet);
                    report.add(user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return report;
    }

    @Override
    public void checkDataUnique(String titleEn, String titleUa) {
        try (
                Connection connection = source.getConnection();
                PreparedStatement titleEnStatement = connection.prepareStatement(manager.getProperty("db.publication.query.get.title.en"));
                PreparedStatement titleUaStatement = connection.prepareStatement(manager.getProperty("db.publication.query.get.title.ua"))
        ) {
            titleEnStatement.setString(1, titleEn);
            try (ResultSet resultSet = titleEnStatement.executeQuery()) {
                if (resultSet.next()){throw new NotUniqueTitleEnException();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
            titleUaStatement.setString(1, titleUa);
            try (ResultSet resultSet = titleUaStatement.executeQuery()) {
                if (resultSet.next()){throw new NotUniqueTitleUaException();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<PublicationDto> getAllMultiLanguagePublication() {
        List<PublicationDto> publicationsDto = new ArrayList<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(manager.getProperty("db.publication.query.get.all"))
        ) {
            while (resultSet.next()) {
                PublicationDto publicationDTO = mapper.extractFromResultSet(resultSet);
                publicationsDto.add(publicationDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return publicationsDto;
    }

    @Override
    public void insertPublicationDto(PublicationDto dto) {
        int result=0;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.update"))
        ) {
            statement.setString(1,dto.getTitleEn());
            statement.setString(2,dto.getTitleUa());
            statement.setString(3,dto.getAuthor());
            statement.setString(4,dto.getGenreEn());
            statement.setString(5,dto.getGenreUa());
            statement.setFloat(6,dto.getPrice().floatValue());//TODO guess what to do with money
            statement.setString(7,dto.getDescriptionEn());//TODO guess how put right name
            statement.setString(8,dto.getDescriptionUa());
            statement.setBlob(9, connection.createBlob());//TODO guess how put right name
            statement.setInt(10,dto.getId());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<PublicationDto> search(Map<String, String> searchParameters) {
        List<PublicationDto> publications = new ArrayList<>();
        PublicationDTOMapper mapper = new PublicationDTOMapper();
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(manager.getProperty("db.publication.query.search"));

        ) {
            System.out.println(statement);
            statement.setString(1, "%"+searchParameters.getOrDefault("title", "")+"%");
            System.out.println(statement);
            statement.setString(2, "%"+searchParameters.getOrDefault("title", "")+"%");

            System.out.println(statement);
            statement.setString(3, "%"+searchParameters.getOrDefault("genre","")+"%");
            statement.setString(4, "%"+searchParameters.getOrDefault("genre","")+"%");
            statement.setFloat(5, Float.parseFloat(searchParameters.getOrDefault("leftPriceBoundary", "0.0")));
            statement.setFloat(6, Float.parseFloat(searchParameters.getOrDefault("rightPriceBoundary", "10000.0")));

            /*if (searchParameters.get("leftPriceBoundary") != null) {
                statement.setString(5, "and price>="+searchParameters.get("leftPriceBoundary"));
            } else {
                statement.setString(5, "");
            }
            if (searchParameters.get("rightPriceBoundary") != null) {
                statement.setString(6, "and price<="+searchParameters.get("rightPriceBoundary"));
            } else {
                statement.setString(6, "");
            }
*/
            System.out.println(statement);

            try(ResultSet resultSet= statement.executeQuery()){
                while (resultSet.next()) {
                    PublicationDto publicationDto = mapper.extractFromResultSet(resultSet);
                    publications.add(publicationDto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        if (publications.isEmpty()){
            throw new NothingFoundException();
        }
        return publications;
    }

    @Override
    public void close() {

    }
}

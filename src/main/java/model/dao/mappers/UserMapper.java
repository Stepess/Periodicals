package model.dao.mappers;

import model.entity.User;
import model.service.builders.UserBuilder;
import model.service.resource.manager.DBFieldsManager;
import model.service.resource.manager.ResourceManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
         UserBuilder builder = new UserBuilder(resultSet.getInt("id"))
                .buildLogin(resultSet.getString("login"))
                .buildPassword(resultSet.getString("password"))
                .buildEmail(resultSet.getString("email"))
                .buildRole(User.RoleEnum.valueOf(resultSet.getString("role").toUpperCase()))
                .buildFirstName(resultSet.getString("first_name"))
                .buildLastName(resultSet.getString("last_name"))
                .buildAddress(resultSet.getString("address"))
                .buildAccount(resultSet.getBigDecimal("account"));
        return builder.build();

        //TODO deletePublication code
        /*User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(User.RoleEnum.valueOf(resultSet.getString("role").toUpperCase()));
        user.setFirstName(resultSet.getString(manager.getProperty("db.user.first.name")));
        user.setLastName(resultSet.getString(manager.getProperty("db.user.last.name")));
        user.setAddress(resultSet.getString("address"));
        user.setAccount(resultSet.getBigDecimal("account"));*/

    }
}


/*
*  ResourceManager manager = new DBFieldsManager(new Locale("uk", "UA"));//TODO guess how change lang
        UserBuilder builder = new UserBuilder(resultSet.getInt("id"))
                .buildLogin(resultSet.getString("login"))
                .buildPassword(resultSet.getString("password"))
                .buildEmail(resultSet.getString("email"))
                .buildRole(User.RoleEnum.valueOf(resultSet.getString("role").toUpperCase()))
                .buildFirstName(resultSet.getString(manager.getProperty("db.user.first.name")) == null ?
                        resultSet.getString("first_name_en") :
                        resultSet.getString(manager.getProperty("db.user.first.name")))
                .buildLastName(resultSet.getString(manager.getProperty("db.user.last.name")) == null ?
                        resultSet.getString("last_name_en") :
                        resultSet.getString(manager.getProperty("db.user.last.name")))
                .buildAddress(resultSet.getString(manager.getProperty("db.user.address")) == null ?
                        resultSet.getString("address_en") :
                        resultSet.getString(manager.getProperty("db.user.address")))
                .buildAccount(resultSet.getBigDecimal("account"));*/
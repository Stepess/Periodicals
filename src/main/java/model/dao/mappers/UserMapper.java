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
    }
}
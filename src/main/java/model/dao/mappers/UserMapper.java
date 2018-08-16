package model.dao.mappers;

import model.entity.User;
import model.service.resource.manager.DBFieldsManager;
import model.service.resource.manager.ResourceManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        ResourceManager manager = new DBFieldsManager();//TODO guess how change lang
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(User.RoleEnum.valueOf(resultSet.getString("role").toUpperCase()));
        user.setFirstName(resultSet.getString(manager.getProperty("db.user.first.name")));
        user.setLastName(resultSet.getString(manager.getProperty("db.user.last.name")));
        user.setAddress(resultSet.getString("address"));
        user.setAccount(resultSet.getBigDecimal("account"));
        return user;
    }
}

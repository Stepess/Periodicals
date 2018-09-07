package model.service;

import model.entity.User;
import model.exception.NotUniqueEmailException;
import model.exception.NotUniqueLoginException;
import model.service.builders.UserBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UserServiceTest {
    private static UserService service;

    @BeforeClass
    public static void init() {
        service = new UserService();
    }

    @Test
    public void GivenUserLoginSenpaiWhenCheckUserExistenceThenUserExist() {
        String login = "senpai";
        boolean result = service.isUserExist(login);
        assertTrue(result);
    }

    @Test
    public void GivenUserLogin123Login321WhenCheckUserExistenceThenUserNotExist() {
        String login = "123Login321";
        boolean result = service.isUserExist(login);
        assertFalse(result);
    }

    @Test
    public void GivenUserLoginSenpaiWhenGetRoleByLoginThenUserRoleAdmin() {
        String login = "senpai";
        User.RoleEnum expected = User.RoleEnum.ADMIN;
        User.RoleEnum actual = service.getUserRole(login);
        assertEquals(expected, actual);
    }

    @Test
    public void GivenUserLoginSenpaiWhenGetRoleByLoginThenUserRoleNotGuest() {
        String login = "senpai";
        User.RoleEnum expected = User.RoleEnum.GUEST;
        User.RoleEnum actual = service.getUserRole(login);
        assertNotEquals(expected, actual);
    }

    @Test
    public void GivenNewUserWhenUserAddedToBDThenBusinessLayerMD5HashEqualsMD5HashAtSQL() {
        String login = "test";
        String password = "testik";
        String md5HashedPassword = service.MD5(password);
        User user = new UserBuilder()
                .buildLogin(login)
                .buildPassword(password)
                .buildEmail("test@test.com")
                .buildFirstName("Test")
                .buildLastName("Test")
                .buildRole(User.RoleEnum.USER)
                .buildAddress("Test, test, test, 13")
                .buildAccount(new BigDecimal(15.5d))
                .build();

        service.registerUser(user);

        user = service.getUserByLogin(login);
        String passwordFromDb = user.getPassword();

        assertEquals(md5HashedPassword, passwordFromDb);
    }

    @Test(expected = NotUniqueLoginException.class)
    public void GivenLoginThatAlreadyRegisteredWhenCheckLoginUniqueThenThrowException() {
        String login = "senpai";
        service.checkDataUnique(login, "");
    }

    @Test(expected = NotUniqueEmailException.class)
    public void GivenEmailThatAlreadyRegisteredWhenCheckEmailUniqueThenThrowException() {
        String email = "stepanersh@gmail.com";
        service.checkDataUnique("", email);
    }
}
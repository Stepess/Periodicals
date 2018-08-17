package model.service.builders;

import model.entity.User;

import java.math.BigDecimal;

public class UserBuilder {
    private int id;
    private String login;
    private String email;
    private User.RoleEnum role;
    private String firstName;
    private String lastName;
    private String address;
    private BigDecimal account;

    public UserBuilder(int id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public UserBuilder buildRole(User.RoleEnum role) {
        this.role = role;
        return this;
    }

    public UserBuilder buildFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder buildLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder buildAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder buildAccount(BigDecimal account) {
        this.account = account;
        return this;
    }

    public User build() {
        return new User(this);
    }


    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public User.RoleEnum getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getAccount() {
        return account;
    }
}

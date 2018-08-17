package model.entity;

import model.service.builders.UserBuilder;

import java.math.BigDecimal;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    public enum RoleEnum {
        USER, ADMIN
    }
    private RoleEnum role;
    private String firstName;
    private String lastName;
    private String address;
    private BigDecimal account;


    public User(int id, String login, String email, RoleEnum role, String firstName, String lastName, String address, BigDecimal account) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.account = account;
    }

    public User(UserBuilder builder){
        this.id = builder.getId();
        this.login = builder.getLogin();
        this.email = builder.getEmail();
        this.role = builder.getRole();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.address = builder.getAddress();
        this.account = builder.getAccount();
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public RoleEnum getRole() {
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

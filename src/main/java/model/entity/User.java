package model.entity;

import model.service.builders.UserBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    public enum RoleEnum {
        GUEST("guest"), USER("user"), ADMIN("admin");

        private String value;

        RoleEnum(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }

    }
    private RoleEnum role;
    private String firstName;
    private String lastName;
    private String address;
    private BigDecimal account;
    private Map<String, String> nationalFields;

    public User(UserBuilder builder){
        this.id = builder.getId();
        this.login = builder.getLogin();
        this.password = builder.getPassword();
        this.email = builder.getEmail();
        this.role = builder.getRole();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.address = builder.getAddress();
        this.account = builder.getAccount();
        nationalFields = new HashMap<>();
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

    public Map<String, String> getNationalFields() {
        return nationalFields;
    }

    public String getNationalField(String key) {
        return nationalFields.get(key);
    }

    public void setNationalField(String key, String field) {
        this.nationalFields.put(key, field);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", account=" + account +
                '}';
    }
}

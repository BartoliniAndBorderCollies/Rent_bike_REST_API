package com.klodnicki.bike.rest.API.Bike.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Bike_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Must have a value.")
    private String name;
    @Digits(integer = 9, fraction = 0, message = "Must have nine numbers")
    private String phoneNumber;
    @Email
    private String emailAddress;
    @NotNull(message = "Must have a value.")
    private int accountNumber;
    @NotBlank(message = "Must have a value.")
    private String login;
    @NotBlank(message = "Must have a value.")
    @Size(min = 6, message = "Must have minimum six characters ")
    private String password;
    @NotNull(message = "Must have a value.")
    private boolean isAccountValid;
    @NotBlank(message = "Must have a value.")
    private String role;

    public User(Long id, String name, String phoneNumber, String emailAddress, int accountNumber, String login,
                String password, boolean isAccountValid, String role) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.accountNumber = accountNumber;
        this.login = login;
        this.password = password;
        this.isAccountValid = isAccountValid;
        this.role = role;
    }

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountValid() {
        return isAccountValid;
    }

    public void setAccountValid(boolean accountValid) {
        isAccountValid = accountValid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

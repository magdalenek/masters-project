package com.charitable.user.model;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;
    private String standardUsername;
    private String standardPassword;

    private String fullName;
    private String username;
    private String name;
    private String password;

    private String email;
    private String token;

    private boolean isVerifiedEmail;

    public User(String standardUsername, String standardPassword, String fullName, String username, String name, String password, String email, String token, boolean isVerifiedEmail) {
        this.standardUsername = standardUsername;
        this.standardPassword = standardPassword;
        this.fullName = fullName;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.token = token;
        this.isVerifiedEmail = isVerifiedEmail;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStandardUsername() {
        return standardUsername;
    }

    public void setStandardUsername(String standardUsername) {
        this.standardUsername = standardUsername;
    }

    public String getStandardPassword() {
        return standardPassword;
    }

    public void setStandardPassword(String standardPassword) {
        this.standardPassword = standardPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isVerifiedEmail() {
        return isVerifiedEmail;
    }

    public void setVerifiedEmail(boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }



}

package com.lending.securityapp.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`User`")
public final  class User {
    @Id
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

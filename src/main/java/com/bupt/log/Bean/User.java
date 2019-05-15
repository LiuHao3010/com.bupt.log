package com.bupt.log.Bean;

import org.springframework.data.annotation.Id;

public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Id
    private String username;
    private String password;
}

package com.bupt.log.Bean;

import org.omg.PortableServer.THREAD_POLICY_ID;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public User(String username,String password,String role){
        this.username=username;
        this.password=password;
        this.role=role;
    }
    public User(){
    }
    private String role;
}

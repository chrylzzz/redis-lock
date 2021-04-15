package com.chryl.entity;

import lombok.Data;

/**
 * @author Chr.yl
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String status;

    private String roles;

    private String rememberMe;

    public User() {
    }

    public User(Integer id, String username, String password, String status, String roles, String rememberMe) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
        this.rememberMe = rememberMe;
    }
}

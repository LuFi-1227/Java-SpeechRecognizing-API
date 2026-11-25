package com.application.users.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class User {
    private UserId id;
    private String name;
    private String phone;
    private String email;

    public User(String name, String phone, String email){
        this.id = new UserId();
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

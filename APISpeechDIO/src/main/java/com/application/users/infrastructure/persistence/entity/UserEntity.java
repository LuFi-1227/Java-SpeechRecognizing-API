package com.application.users.infrastructure.persistence.entity;

import java.util.UUID;

import com.application.users.domain.User;
import com.application.users.domain.UserId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class UserEntity {
    @Id 
    private UUID id;
    private String name;
    private String phone;
    private String email;

    public static UserEntity from(User user){
        return new UserEntity(user.getId().id(), user.getName(), user.getPhone(), user.getEmail());
    }

    public User toDomain(){
        return new User(new UserId(this.id), this.name, this.phone, this.email);
    }
}

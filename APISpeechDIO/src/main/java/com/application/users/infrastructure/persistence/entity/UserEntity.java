package com.application.users.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.application.users.domain.Phone;
import com.application.users.domain.User;
import com.application.users.domain.UserId;

import jakarta.persistence.Column;
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
    @Column (unique = true, nullable = false)
    private String phone;
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public UserEntity(UUID id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public static UserEntity from(User user){
        return new UserEntity(user.getId().id(), user.getName(), user.getPhone().value(), user.getEmail());
    }

    public User toDomain(){
        return new User(new UserId(this.id), this.name, new Phone(this.phone), this.email);
    }
}

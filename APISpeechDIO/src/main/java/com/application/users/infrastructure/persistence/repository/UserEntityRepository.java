package com.application.users.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.application.users.infrastructure.persistence.entity.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, UUID>{
    Optional<UserEntity> findByPhone(String phone);
}

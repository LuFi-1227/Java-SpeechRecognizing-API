package com.application.users.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.application.users.domain.User;
import com.application.users.domain.UserId;
import com.application.users.domain.UserRepository;
import com.application.users.infrastructure.persistence.entity.UserEntity;

import io.micrometer.common.lang.NonNull;

@Repository 
public class JPAUserRepository implements UserRepository{
    private UserEntityRepository userEntityRepository;

    
    public JPAUserRepository(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public User save(@NonNull User user) {
        return userEntityRepository.save(UserEntity.from(user)).toDomain();
    }

    @Override
    public List<User> listAllUsers() {
        var iterable = userEntityRepository.findAll();
        List<User> userList = new ArrayList<>();
        for (UserEntity user : iterable) {
            userList.add(user.toDomain());
        }
        return userList;
    }

    @Override
    public User findById(@NonNull UserId id) {
        return userEntityRepository.findById(id.id()).orElse(null).toDomain();
    }

    @Override
    public User findByPhone(String phone) {
        return userEntityRepository.findByPhone(phone).orElse(null).toDomain();
    }

    @Override
    public User update(UserId id, User user) {
        return userEntityRepository.update(UserEntity.from(user), id.id()).orElse(null).toDomain();
    }

    @Override
    public void delete(UserId id) {
        userEntityRepository.deleteById(id.id());
    }
    
}

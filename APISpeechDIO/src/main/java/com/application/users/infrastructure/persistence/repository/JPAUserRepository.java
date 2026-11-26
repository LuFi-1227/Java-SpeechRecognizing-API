package com.application.users.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<User> findById(@NonNull UserId id) {
        return userEntityRepository.findById(id.id()).map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userEntityRepository.findByPhone(phone).map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> update(UserId id, User user) {
        return userEntityRepository.findById(id.id())
                .map(existingUser -> {
                    UserEntity updatedUser = UserEntity.from(user);
                    updatedUser.setId(existingUser.getId());
                    updatedUser.setCreatedAt(existingUser.getCreatedAt());

                    return userEntityRepository.save(updatedUser).toDomain();
                });
    }

    @Override
    public void delete(UserId id) {
        userEntityRepository.deleteById(id.id());
    }
    
}

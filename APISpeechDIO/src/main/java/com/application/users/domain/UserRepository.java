package com.application.users.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    List<User> listAllUsers();
    Optional<User> findById(UserId id);
    Optional<User> findByPhone(String phone);
    Optional<User> update(UserId id, User user);
    void delete(UserId id);
}

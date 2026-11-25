package com.application.users.domain;

import java.util.List;

public interface UserRepository {
    User save(User user);
    List<User> listAllUsers();
    User findById(UserId id);
    User findByPhone(String phone);
    User update(UserId id, User user);
    void delete(UserId id);
}

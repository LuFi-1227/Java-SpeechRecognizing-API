package com.application.users.application;

import org.springframework.stereotype.Service;

import com.application.users.domain.UserRepository;
import java.util.List;
import com.application.users.application.output.UserOutput;

@Service 
public class ListUsersUseCase {
    private final UserRepository userRepository;

    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserOutput> execute(){
        return userRepository.listAllUsers().stream().map(UserOutput::from).toList();
    }
}

package com.application.users.infrastructure.http;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.application.users.application.DeleteUserUseCase;
import com.application.users.application.FindUserByIdUseCase;
import com.application.users.application.FindUserByPhoneUseCase;
import com.application.users.application.ListUsersUseCase;
import com.application.users.application.PersistUserUseCase;
import com.application.users.application.UpdateUserUseCase;
import com.application.users.infrastructure.http.requests.UpdateUserRequest;
import com.application.users.infrastructure.http.requests.UserIdRequest;
import com.application.users.infrastructure.http.requests.UserPhoneRequest;
import com.application.users.infrastructure.http.requests.UserRequest;
import com.application.users.infrastructure.http.responses.UserResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RestController 
@RequestMapping ("/users")
public class UserController {
    private DeleteUserUseCase deleteUserUseCase;
    private FindUserByIdUseCase findUserByIdUseCase;
    private FindUserByPhoneUseCase findUserByPhoneUseCase;
    private ListUsersUseCase listUsersUseCase;
    private PersistUserUseCase persistUserUseCase;
    private UpdateUserUseCase updateUserUseCase;

    public UserController(DeleteUserUseCase deleteUserUseCase, FindUserByIdUseCase findUserByIdUseCase,
            FindUserByPhoneUseCase findUserByPhoneUseCase, ListUsersUseCase listUsersUseCase,
            PersistUserUseCase persistUserUseCase, UpdateUserUseCase updateUserUseCase) {
        this.deleteUserUseCase = deleteUserUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.findUserByPhoneUseCase = findUserByPhoneUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.persistUserUseCase = persistUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @GetMapping 
    @ResponseStatus (HttpStatus.OK)
    public List<UserResponse> findAll(){
        return this.listUsersUseCase.execute().stream().map(UserResponse::from).toList();
    }

    @GetMapping (params = "id")
    @ResponseStatus (HttpStatus.OK)
    public UserResponse findById(@ModelAttribute UserIdRequest request){
        return UserResponse.from(this.findUserByIdUseCase.execute(request.toInput()));
    }

    @GetMapping (params = "phone")
    @ResponseStatus (HttpStatus.OK)
    public UserResponse findByPhone(@ModelAttribute UserPhoneRequest request){
        return UserResponse.from(this.findUserByPhoneUseCase.execute(request.toInput()));
    }

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public UserResponse persistUser(@RequestBody UserRequest request){
        return UserResponse.from(this.persistUserUseCase.execute(request.toInput()));
    }

    @PutMapping 
    @ResponseStatus (HttpStatus.OK)
    public UserResponse updateUser(@RequestBody UpdateUserRequest request){
        return UserResponse.from(this.updateUserUseCase.execute(request.toInput(), new UserIdRequest(request.id()).toInput()));
    }

    @DeleteMapping (params = "id")
    @ResponseStatus (HttpStatus.OK)
    public void deleteUser(@ModelAttribute UserIdRequest request){
        this.deleteUserUseCase.execute(request.toInput());
    }
}

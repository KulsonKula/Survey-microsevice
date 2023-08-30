package com.example.survey.controller;

import com.example.survey.model.Users;
import com.example.survey.repository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
public class UsersController {
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DeleteMapping("/delete/{user_id}")
    @Operation(
            description = "Delete a specific User by ID",
            summary = "Delete User",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find user",
                            responseCode = "204"
                    )
            }
    )
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Integer user_id) {
        if (usersRepository.findById(user_id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        usersRepository.deleteById(Long.valueOf(user_id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    @Operation(
            description = "Add user to database, id will be generated automatically. Username and Password cannot be null, must be at least 5 characters, max 30. ",
            summary = "Add user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username of Password are not valid",
                            responseCode = "400"
                    )
            }
    )
    public ResponseEntity<Integer> createUser(@RequestBody Users users) {
        if (users.getPassword() == null || users.getUsername() == null || Objects.equals(users.getPassword(), "") || Objects.equals(users.getUsername(), "")) {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
        if (users.getUsername().length() < 3 || users.getUsername().length() > 30 || users.getPassword().length() < 3 || users.getPassword().length() > 30) {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
        users.setId(null);
        usersRepository.save(users);
        return new ResponseEntity<>(users.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    @Operation(
            description = "Check if user with given username and password are in the database and return his ID",
            summary = "Check if user exist"
    )
    public ResponseEntity<Integer> getUser(@ParameterObject Users users) {
        Users newUsers = usersRepository.findByUsernameAndPassword(users.getUsername(), users.getPassword());
        if (newUsers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newUsers.getId(), HttpStatus.OK);
    }

    @PostMapping("/edit")
    @Operation(
            description = "Update user",
            summary = "Update user"

    )
    public ResponseEntity<HttpStatus> updateUser(@RequestBody Users users) {
        usersRepository.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

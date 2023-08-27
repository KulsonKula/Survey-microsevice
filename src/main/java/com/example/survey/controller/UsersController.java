package com.example.survey.controller;

import com.example.survey.model.Users;
import com.example.survey.repository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            description = "Delete a specific User"
    )
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Integer user_id) {
        usersRepository.deleteById(Long.valueOf(user_id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    @Operation(
            description = "Add user to database"
    )
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        users.setId(null);
        usersRepository.save(users);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    @Operation(
            description = "Check if username and password are in the database"
    )
    public ResponseEntity<Integer> getUser(@ParameterObject Users users) {
        Users newUsers = usersRepository.findByUsernameAndPassword(users.getUsername(), users.getPassword());
        if (newUsers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newUsers.getId(), HttpStatus.OK);
    }

    @PostMapping("/edit/{user_id}")
    @Operation(
            description = "Update user"
    )
    public ResponseEntity<HttpStatus> updateUser(@RequestBody Users users, @PathVariable int user_id) {
        users.setId(user_id);
        usersRepository.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

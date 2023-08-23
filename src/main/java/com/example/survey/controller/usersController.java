package com.example.survey.controller;

import com.example.survey.model.Users;
import com.example.survey.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class usersController {
    private final UsersRepository usersRepository;

    public usersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Integer user_id) {
        usersRepository.deleteById(Long.valueOf(user_id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        user.setId(null);
        usersRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
}

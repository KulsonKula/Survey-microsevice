package com.example.survey.controller;

import com.example.survey.model.Users;
import com.example.survey.repository.UsersRepository;
import org.springdoc.core.annotations.ParameterObject;
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        users.setId(null);
        usersRepository.save(users);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Integer> getUser(@ParameterObject Users users) {
        Users newUsers = usersRepository.findByUsernameAndPassword(users.getUsername(), users.getPassword());
        if (newUsers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newUsers.getId(), HttpStatus.OK);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody Users users, @PathVariable int user_id) {
        users.setId(user_id);
        usersRepository.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

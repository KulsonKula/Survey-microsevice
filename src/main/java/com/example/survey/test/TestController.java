package com.example.survey.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @GetMapping("api/test")
    public ResponseEntity<HttpStatus> connectionTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

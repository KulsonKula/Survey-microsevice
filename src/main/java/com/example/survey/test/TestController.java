package com.example.survey.test;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ConnectionTest")
@RestController

public class TestController {

    @GetMapping("api/test")
    public ResponseEntity<HttpStatus> connectionTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

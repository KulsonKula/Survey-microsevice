package com.example.survey.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ConnectionTest",
        description = "Test for all type of Api to check if CORS would block communication")
@RestController

public class TestController {

    @Operation(
            description = "",
            summary = "Test for GET api"
    )
    @GetMapping("api/test")
    public ResponseEntity<Integer> connectionTestGET() {
        return new ResponseEntity<>(4, HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Test for POST api"
    )
    @PostMapping("api/test")
    public ResponseEntity<HttpStatus> connectionTestPOST() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Test for DELETE api"
    )
    @DeleteMapping("api/test")
    public ResponseEntity<HttpStatus> connectionTestDELETE() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Test for PUT api"
    )
    @PutMapping("api/test")
    public ResponseEntity<HttpStatus> connectionTestPUT() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

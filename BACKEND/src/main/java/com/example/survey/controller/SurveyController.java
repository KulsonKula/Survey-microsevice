package com.example.survey.controller;

import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;

@Tag(name = "Survey")
@RestController
@RequestMapping("/api/user/")
public class SurveyController {

    private final SurveyRepository surveyRepository;
    private final UsersRepository usersRepository;


    public SurveyController(SurveyRepository surveyRepository, UsersRepository usersRepository) {
        this.surveyRepository = surveyRepository;
        this.usersRepository = usersRepository;
    }

    @Operation(
            description = "",
            summary = "Return all survey that belong to user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find survey",
                            responseCode = "204"
                    )
            }
    )
    @GetMapping("/{user_id}/survey/all")
    public ResponseEntity<List<Survey>> getAllSurveyByUser(@PathVariable int user_id) {
        List<Survey> survey = surveyRepository.findByUser_id(user_id);
        if (survey.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @Operation(
            description = "Add survey do database, ID and data of creating will be generated automatically",
            summary = "Add survey do database",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad request - title are too short or status is null",
                            responseCode = "400"
                    )
            }
    )
    @PutMapping("/{user_id}/survey/add")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey surveyRequest, @PathVariable long user_id) {
        if (validateSurvey(surveyRequest)) {
            surveyRequest.setUser(usersRepository.findById(user_id));
            surveyRequest.setId(null);

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            surveyRequest.setCreated_at(date);

            char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")).toCharArray();
            String accessCode = RandomStringUtils.random(11, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
            surveyRequest.setAccessCode((accessCode));

            surveyRepository.save(surveyRequest);
            return new ResponseEntity<>(surveyRequest, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(
            description = "",
            summary = "Get a specific survey",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find survey",
                            responseCode = "204"
                    )
            }
    )
    @GetMapping("/{user_id}/survey/get/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable int id, @PathVariable int user_id) {
        Survey survey = surveyRepository.findById(id);
        if (survey == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Delete a specific survey",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find survey",
                            responseCode = "204"
                    )
            }
    )
    @DeleteMapping("/{user_id}/survey/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable int id, @PathVariable int user_id) {
        if (surveyRepository.findById(user_id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        surveyRepository.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Edit survey",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad request - title are too short or status is null",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/{user_id}/survey/edit")
    public ResponseEntity<HttpStatus> updateSurvey(@PathVariable int user_id, @RequestBody Survey survey) {
        if (validateSurvey(survey)) {
            survey.setUser(usersRepository.findById(user_id));
            surveyRepository.save(survey);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/{user_id}/survey/code/{accessCode}")
    public ResponseEntity<Survey> getSurveyByCode(@PathVariable int user_id, @PathVariable String accessCode) {

        if (validateAccessCode(accessCode)) {
            Survey survey = surveyRepository.findByAccessCode(accessCode);
            if (survey != null) {
                return new ResponseEntity<>(survey, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public boolean validateSurvey(Survey survey) {
        return survey.getTitle().length() >= 5 && survey.getStatus() != null && !Objects.equals(survey.getStatus(), "");
    }

    public boolean validateAccessCode(String accessCode) {
        return !Objects.equals(accessCode, "") && accessCode != null;
    }
}
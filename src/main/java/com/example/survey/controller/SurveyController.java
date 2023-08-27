package com.example.survey.controller;

import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UsersRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{user_id}/survey/all")
    public ResponseEntity<List<Survey>> getAllSurveyByUser(@PathVariable int user_id) {
        List<Survey> survey = surveyRepository.findByUser_id(user_id);
        if (survey.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @PutMapping("/{user_id}/survey/add")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey surveyRequest, @PathVariable long user_id) {
        surveyRequest.setUser(usersRepository.findById(user_id));
        surveyRequest.setId(null);

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        surveyRequest.setCreated_at(date);

        surveyRepository.save(surveyRequest);
        return new ResponseEntity<>(surveyRequest, HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}/survey/get/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable int id, @PathVariable int user_id) {
        Survey survey = surveyRepository.findById(id);
        if (survey == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}/survey/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable int id, @PathVariable int user_id) {
        surveyRepository.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{user_id}/survey/edit")
    public ResponseEntity<HttpStatus> updateSurvey(@PathVariable int user_id, @RequestBody Survey survey) {
        survey.setUser(usersRepository.findById(user_id));
        surveyRepository.save(survey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
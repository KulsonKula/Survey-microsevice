package com.example.survey.controller;

import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class surveyController {

    private final SurveyRepository surveyRepository;

    public surveyController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/{user_id}/survey/")
    public ResponseEntity<List<Survey>> getSurvey(@PathVariable int user_id) {
        List<Survey> survey = surveyRepository.findByUser_id(user_id);
        if (survey.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @GetMapping("/survey/{id}")
    public ResponseEntity<List<Survey>> getSurveyById(@PathVariable int id) {
        List<Survey> survey = surveyRepository.findById(id);
        if (survey.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @DeleteMapping("/survey/{id}")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable int id) {
        surveyRepository.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

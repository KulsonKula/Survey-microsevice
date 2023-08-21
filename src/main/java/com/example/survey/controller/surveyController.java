package com.example.survey.controller;

import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class surveyController {

    private final SurveyRepository surveyRepository;

    public surveyController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/{user_id}/survey/{id}")
    public ResponseEntity<List<Survey>> getSurveyById(@PathVariable int id, @PathVariable String user_id) {
        List<Survey> survey = surveyRepository.findById(id);
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }
}

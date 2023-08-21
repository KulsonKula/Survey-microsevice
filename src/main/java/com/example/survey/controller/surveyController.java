package com.example.survey.controller;

import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class surveyController {

    private final SurveyRepository surveyRepository;


    public surveyController(SurveyRepository surveyRepository, UsersRepository usersRepository) {
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
    public ResponseEntity<Survey> getSurveyById(@PathVariable int id) {
        Survey survey = surveyRepository.findById(id);
        if (survey == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    @DeleteMapping("/survey/{id}")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable int id) {
        surveyRepository.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

// TODO
//    @PostMapping("/{user_id}//survey")
//    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey, @PathVariable int user_id) {
//        Survey newSurvey = surveyRepository.save(new Survey(survey.getTitle(), survey.getStatus(), usersRepository.findById((long) user_id));
//        return new ResponseEntity<>(newSurvey, HttpStatus.CREATED);
//    }

}

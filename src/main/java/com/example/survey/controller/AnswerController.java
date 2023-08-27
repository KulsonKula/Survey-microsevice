package com.example.survey.controller;

import com.example.survey.model.Answer;
import com.example.survey.repository.AnswerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerController {
    AnswerRepository answerRepository;
    SurveyController surveyController;

    public AnswerController(AnswerRepository answerRepository, SurveyController surveyController) {
        this.answerRepository = answerRepository;
        this.surveyController = surveyController;
    }


    @GetMapping("/{question_id}/answers")
    public ResponseEntity<List<Answer>> getAllAnswers(@PathVariable int question_id) {
        List<Answer> answer = answerRepository.findByQuestion_id(question_id);
        if (answer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}

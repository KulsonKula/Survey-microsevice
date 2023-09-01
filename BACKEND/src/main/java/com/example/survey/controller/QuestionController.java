package com.example.survey.controller;

import com.example.survey.model.Question;
import com.example.survey.repository.QuestionRepository;
import com.example.survey.repository.SurveyRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Question")
@RestController
@RequestMapping("/api/survey")
public class QuestionController {

    QuestionRepository questionRepository;
    SurveyRepository surveyRepository;

    public QuestionController(QuestionRepository questionRepository, SurveyRepository surveyRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }


    @Operation(
            description = "",
            summary = "Get all questions that belongs to survey",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find question",
                            responseCode = "204"
                    )
            }
    )
    @GetMapping("/{survey_id}/question/all")
    public ResponseEntity<List<Question>> findAllQuestion(@PathVariable int survey_id) {
        List<Question> questions = questionRepository.findBySurvey_idOrderBySurveyAsc(survey_id);
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Get a specific question",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find question",
                            responseCode = "204"
                    )
            }
    )
    @GetMapping("/{survey_id}/question/get/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable int survey_id, @PathVariable Integer id) {
        Question question = questionRepository.findById(id);
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Delete a specific question",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find question",
                            responseCode = "404"
                    )
            }

    )
    @DeleteMapping("/{survey_id}/question/delete/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionById(@PathVariable int survey_id, @PathVariable Integer id) {
        if (!questionRepository.existsById(Long.valueOf(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questionRepository.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Create a specific question",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/{survey_id}/question/add")
    public ResponseEntity<HttpStatus> createQuestion(@PathVariable int survey_id, @RequestBody Question question) {
        question.setSurvey(surveyRepository.findById(survey_id));
        question.setId(null);
        questionRepository.save(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Edit a specific survey",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/{survey_id}/question/edit")
    public ResponseEntity<HttpStatus> updateQuestion(@PathVariable int survey_id, @RequestBody Question question) {
        if (questionRepository.findByIdAndSequence(question.getId(), question.getSequence()) != null) {
            question.setSurvey(surveyRepository.findById(survey_id));
            questionRepository.save(question);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/{survey_id}/question/{id}/position/{position_number}")
    @Operation(
            description = "Change the question's position to {position_number}. Change position of all question between position's and given so that they fit ",
            summary = "Change position of question"
    )
    public ResponseEntity<HttpStatus> changeQuestionPosition(@PathVariable int survey_id, @PathVariable int id, @PathVariable int position_number) {
        if (position_number <= 0 || questionRepository.findById(id) == null || questionRepository.findBySurvey_id(survey_id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int i;
        List<Question> newQuestions;
        Question question = questionRepository.findById(id);

        if (position_number > questionRepository.findById(id).getSequence()) {
            newQuestions = questionRepository.findBySurvey_idAndSequenceBetween(survey_id, questionRepository.findById(id).getSequence() + 1, position_number);
            i = -1;
        } else {
            newQuestions = questionRepository.findBySurvey_idAndSequenceBetween(survey_id, position_number, questionRepository.findById(id).getSequence() - 1);
            i = 1;
        }
        for (Question q : newQuestions) {
            q.setSequence(q.getSequence() + i);
            questionRepository.save(q);
        }
        int max_position = questionRepository.findBySurvey_IdOrderBySequenceDesc(survey_id).get(0).getSequence();
        if (position_number > max_position) {
            question.setSequence(max_position + 1);
        } else {
            question.setSequence(position_number);
        }
        questionRepository.save(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

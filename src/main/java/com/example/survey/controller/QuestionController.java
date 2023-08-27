package com.example.survey.controller;

import com.example.survey.model.Question;
import com.example.survey.repository.QuestionRepository;
import com.example.survey.repository.SurveyRepository;
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


    @GetMapping("/{survey_id}/question/all")
    public ResponseEntity<List<Question>> findAllQuestion(@PathVariable int survey_id) {
        List<Question> questions = questionRepository.findBySurvey_idOrderBySurveyAsc(survey_id);
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{survey_id}/question/get/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable int survey_id, @PathVariable Integer id) {
        Question question = questionRepository.findById(id);
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{survey_id}/question/delete/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionById(@PathVariable int survey_id, @PathVariable Integer id) {
        if (!questionRepository.existsById(Long.valueOf(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questionRepository.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{survey_id}/question/add")
    public ResponseEntity<HttpStatus> createQuestion(@PathVariable int survey_id, @RequestBody Question question) {
        question.setSurvey(surveyRepository.findById(survey_id));
        question.setId(null);
        questionRepository.save(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
    public ResponseEntity<List<Question>> changeQuestionPosition(@PathVariable int survey_id, @PathVariable int id, @PathVariable int position_number) {
        if (position_number == 0 || questionRepository.findById(id) == null || questionRepository.findBySurvey_id(survey_id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Question> newQuestions;
        if (position_number > questionRepository.findById(id).getSequence()) {
            newQuestions = questionRepository.findBySurvey_idAndSequenceBetween(survey_id, questionRepository.findById(id).getSequence() + 1, position_number);
            Question question = questionRepository.findById(id);
            for (Question q : newQuestions) {
                q.setSequence(q.getSequence() - 1);
                questionRepository.save(q);
            }
            question.setSequence(position_number);
            questionRepository.save(question);

        } else {
            newQuestions = questionRepository.findBySurvey_idAndSequenceBetween(survey_id, position_number, questionRepository.findById(id).getSequence() - 1);
            Question question = questionRepository.findById(id);
            for (Question q : newQuestions) {
                q.setSequence(q.getSequence() + 1);
                questionRepository.save(q);
            }
            question.setSequence(position_number);
            questionRepository.save(question);
        }
        return new ResponseEntity<>(newQuestions, HttpStatus.OK);
    }


}

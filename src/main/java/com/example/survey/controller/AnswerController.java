package com.example.survey.controller;

import com.example.survey.model.Answer;
import com.example.survey.repository.AnswerRepository;
import com.example.survey.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Answer")
@RestController
@RequestMapping("/api/question")
public class AnswerController {
    AnswerRepository answerRepository;
    QuestionRepository questionRepository;

    public AnswerController(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/{question_id}/answers/all")
    public ResponseEntity<List<Answer>> getAllAnswers(@PathVariable int question_id) {
        List<Answer> answer = answerRepository.findByQuestion_idOrderBySequenceAsc(question_id);
        if (answer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }


    @GetMapping("/{question_id}/answers/get/{id}")
    public ResponseEntity<Answer> findById(@PathVariable int question_id, @PathVariable int id) {
        Answer answer = answerRepository.findById(id);
        if (answer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/{question_id}/answers/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int question_id, @PathVariable int id) {
        answerRepository.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{question_id}/answers/add")
    public ResponseEntity<HttpStatus> createAnswer(@PathVariable int question_id, @RequestBody Answer answer) {

        Answer newAnswer = answerRepository.findByTextAndQuestion_id(answer.getText(), question_id);
        if (newAnswer != null) {
            newAnswer.setData(newAnswer.getData() + 1);
            answerRepository.save(newAnswer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        answer.setId(null);
        answer.setQuestion(questionRepository.findById(question_id));
        answerRepository.save(answer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{question_id}/answers/edit")
    public ResponseEntity<HttpStatus> updateQuestion(@PathVariable int question_id, @RequestBody Answer answer) {
        if (answerRepository.findByIdAndSequence(answer.getId(), answer.getSequence()) != null) {
            answer.setQuestion(questionRepository.findById(question_id));
            answerRepository.save(answer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{question_id}/answers/edit/{id}/increment")
    public ResponseEntity<HttpStatus> increment(@PathVariable int question_id, @PathVariable int id) {
        Answer answer = answerRepository.findById(id);
        answer.setData(answer.getData() + 1);
        answerRepository.save(answer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{question_id}/answer/{id}/position/{position_number}")
    public ResponseEntity<List<Answer>> changeAnswerPosition(@PathVariable int question_id, @PathVariable int id, @PathVariable int position_number) {
        if (position_number == 0 || answerRepository.findById(id) == null || answerRepository.findByQuestion_id(question_id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Answer> newAnswer;
        if (position_number > answerRepository.findById(id).getSequence()) {
            newAnswer = answerRepository.findByQuestion_idAndSequenceBetween(question_id, answerRepository.findById(id).getSequence() + 1, position_number);
            Answer answer = answerRepository.findById(id);
            for (Answer a : newAnswer) {
                a.setSequence(a.getSequence() - 1);
                answerRepository.save(a);
            }
            answer.setSequence(position_number);
            answerRepository.save(answer);
        } else {
            newAnswer = answerRepository.findByQuestion_idAndSequenceBetween(question_id, position_number, answerRepository.findById(id).getSequence() - 1);
            Answer answer = answerRepository.findById(id);
            for (Answer a : newAnswer) {
                a.setSequence(a.getSequence() + 1);
                answerRepository.save(a);
            }
            answer.setSequence(position_number);
            answerRepository.save(answer);
        }


        return new ResponseEntity<>(newAnswer, HttpStatus.OK);
    }
}

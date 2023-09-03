package com.example.survey.controller;

import com.example.survey.model.Answer;
import com.example.survey.repository.AnswerRepository;
import com.example.survey.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @GetMapping("/{question_id}/answer/all")
    @Operation(
            description = "",
            summary = "Return all answers that belongs to question",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find answer",
                            responseCode = "204"
                    )
            }
    )
    public ResponseEntity<List<Answer>> getAllAnswers(@PathVariable int question_id) {
        List<Answer> answer = answerRepository.findByQuestion_idOrderBySequenceAsc(question_id);
        if (answer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Return a specific answer",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cant find answer",
                            responseCode = "204"
                    )
            }
    )
    @GetMapping("/{question_id}/answer/get/{id}")
    public ResponseEntity<Answer> findById(@PathVariable int question_id, @PathVariable int id) {
        Answer answer = answerRepository.findById(id);
        if (answer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @Operation(
            description = "",
            summary = "Delete a specific answer",
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
    @DeleteMapping("/{question_id}/answer/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int question_id, @PathVariable int id) {
        if (!answerRepository.existsById(Long.valueOf(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        answerRepository.deleteById((long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            description = "Dodaje odpwiedz do bazy danych, jezeli pozycja jest nie jest równa -1, jest ona ustawiana automatycznie. Pozycja równa -1 jest zarezerwowana dla odpowiedzi na pytania otwarte. ",
            summary = "Add answer to database",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/{question_id}/answer/add")
    public ResponseEntity<HttpStatus> createAnswer(@PathVariable int question_id, @RequestBody Answer answer) {

        answer.setData(0);
        answer.setId(null);
        answer.setQuestion(questionRepository.findById(question_id));
        if (answer.getSequence() == -1) {
            answerRepository.save(answer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        answer.setSequence(answerRepository.findByQuestion_IdOrderBySequenceDesc(question_id).get(0).getSequence() + 1);
        answerRepository.save(answer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            description = "",
            summary = "Edit answer",
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
    @PostMapping("/{question_id}/answer/edit")
    public ResponseEntity<HttpStatus> updateQuestion(@PathVariable int question_id, @RequestBody Answer answer) {
        if (answerRepository.findByIdAndSequence(answer.getId(), answer.getSequence()) != null) {
            answer.setQuestion(questionRepository.findById(question_id));
            answerRepository.save(answer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(
            description = "",
            summary = "Increment data for specific answer",
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
    @PostMapping("/{question_id}/answer/edit/{id}/increment")
    public ResponseEntity<HttpStatus> increment(@PathVariable int question_id, @PathVariable int id) {
        if (!answerRepository.existsById(Long.valueOf(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Answer answer = answerRepository.findById(id);
        answer.setData(answer.getData() + 1);
        answerRepository.save(answer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{question_id}/answer/{id}/position/{position_number}")
    @Operation(
            description = "Change the answer's position to {position_number}. Change position of all question between answer's and given so that they fit, if {posision_number} is bigger then highest numnber in survey, it will be automaticed set to max  + 1 ",
            summary = "Change position of answer",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400"
                    )}
    )
    public ResponseEntity<HttpStatus> changeAnswerPosition(@PathVariable int question_id, @PathVariable int id, @PathVariable int position_number) {
        if (position_number <= 0 || answerRepository.findById(id) == null || answerRepository.findByQuestion_id(question_id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int i;
        Answer answer = answerRepository.findById(id);
        List<Answer> newAnswer;

        if (position_number > answerRepository.findById(id).getSequence()) {
            newAnswer = answerRepository.findByQuestion_idAndSequenceBetween(question_id, answerRepository.findById(id).getSequence() + 1, position_number);
            i = -1;
        } else {
            newAnswer = answerRepository.findByQuestion_idAndSequenceBetween(question_id, position_number, answerRepository.findById(id).getSequence() - 1);
            i = 1;
        }
        for (Answer a : newAnswer) {
            a.setSequence(a.getSequence() + i);
            answerRepository.save(a);
        }

        int max_position = answerRepository.findByQuestion_IdOrderBySequenceDesc(question_id).get(0).getSequence();
        if (position_number > max_position) {
            answer.setSequence(max_position + 1);
        } else {
            answer.setSequence(position_number);
        }
        answerRepository.save(answer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

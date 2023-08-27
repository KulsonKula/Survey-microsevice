package com.example.survey.repository;

import com.example.survey.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestion_id(int survey_id);

    List<Answer> findByQuestion_idOrderBySequenceAsc(int survey_id);

    Answer findById(int id);

    Answer findByIdAndSequence(Integer id, int sequence);

    Answer findByTextAndQuestion_id(String text, int question_id);

    List<Answer> findByQuestion_idAndSequenceBetween(int question, int min, int max);
}
package com.example.survey.repository;

import com.example.survey.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(long id);

    Question findByIdAndSequence(Integer id, int sequence);

    List<Question> findBySurvey_id(int id);

    List<Question> findBySurvey_idOrderBySurveyAsc(int id);

    List<Question> findBySurvey_idAndSequenceBetween(int survey, int min, int max);

}

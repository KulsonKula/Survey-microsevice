package com.example.survey.repository;

import com.example.survey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    
    List<Survey> findByUser_id(long id);

    Survey findById(long id);
}

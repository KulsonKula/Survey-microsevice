package com.example.survey;

import com.example.survey.controller.AnswerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
class SurveyApplicationTests {
    @Autowired
    private AnswerController test;

    @Test
    void contextLoads() {
        assertThat(test).isNotNull();
    }

}

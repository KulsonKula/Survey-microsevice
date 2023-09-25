"use client";
import { QuestionCreator } from "@/modules/questions/QuestionCreator/QuestionCreator";
import { Button } from "@/modules/ui/Button/Button";
import { useState } from "react";

export const SurveyCreator = () => {
  const [questionsInProgress, setQuestionsInProgress] = useState(0);
  const [submittedQuestions, setSubmittedQuestions] = useState(0);
  const [totalQuestions, setTotalQuestions] = useState(0);

  const onAddQuestion = () => setQuestionsInProgress((q) => q + 1);
  const onSubmitSurvey = () => {
    console.log(questionsInProgress);
  };
  const onSubmitQuestion = () => {
    setQuestionsInProgress((q) => q - 1);
    setSubmittedQuestions((q) => q + 1);
    setTotalQuestions((q) => q + 1);
    console.log("submitted question");
    console.log(questionsInProgress);
    console.log(submittedQuestions);
  };

  return (
    <>
      {submittedQuestions < 1 && (
        <h1>You need at least one question to submit the survey</h1>
      )}
      <div className="flex flex-col">
        {<QuestionCreator onSubmitQuestion={onSubmitQuestion} />}

        <div className="flex justify-center">
          <Button onClick={onAddQuestion} size="md">
            Create a new question
          </Button>
        </div>
        {questionsInProgress === 0 && submittedQuestions === totalQuestions && (
          <h2>You need to submit all questions to submit the survey</h2>
        )}
        <Button
          onClick={onSubmitSurvey}
          size="lg"
          variant="accept"
          disabled={true}
        >
          Submit survey
        </Button>
      </div>
    </>
  );
};

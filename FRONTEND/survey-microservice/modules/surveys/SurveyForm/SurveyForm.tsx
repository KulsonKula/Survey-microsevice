import { useGetQuestionsBySurveyId } from "@/modules/questions/hooks/useGetQuestionsBySurveyId/useGetQuestionsBySurveyId";

type SurveyFormProps = {
  surveyId: number;
};
export const SurveyForm = ({ surveyId }: SurveyFormProps) => {
  const questions = useGetQuestionsBySurveyId(surveyId);
  if (!questions.isSuccess) return <div>error</div>;

  return (
    <div className="flex flex-col">
      {questions.data.map((question) => (
        <span key={question.id}>
          {question.sequence}. {question.text}
          <br />
          {question.type}
        </span>
      ))}
    </div>
  );
};

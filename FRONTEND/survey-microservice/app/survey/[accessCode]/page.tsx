"use client";
import { SurveyForm } from "@/modules/surveys/SurveyForm/SurveyForm";
import { useGetSurvey } from "@/modules/surveys/hooks/useGetSurvey/useGetSurvey";

export default function SurveyPage({
  params,
}: {
  params: { accessCode: string };
}) {
  const survey = useGetSurvey(params.accessCode);

  //TODO display errors
  if (!survey.isSuccess) return <div>error ladowania</div>;

  return (
    <main>
      <h1 className="uppercase text-3xl tracking-widest">
        Survey code: {params.accessCode}
      </h1>
      <SurveyForm surveyId={survey.data.id} />
    </main>
  );
}

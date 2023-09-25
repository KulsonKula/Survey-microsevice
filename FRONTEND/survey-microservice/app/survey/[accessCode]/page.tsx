"use client";
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
      <h1>helo complete this survey pls :))) code {params.accessCode}</h1>
    </main>
  );
}

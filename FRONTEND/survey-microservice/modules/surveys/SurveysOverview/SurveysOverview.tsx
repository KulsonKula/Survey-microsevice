"use client";
import { useGetAllUserSurveys } from "../hooks/useGetAllUserSurveys/useGetAllUserSurveys";
import { SurveyCard } from "./SurveyCard/SurveyCard";

const testCurrentUser = {
  username: "Qlson",
  id: 11,
};
export const SurveysOverview = () => {
  //!! tu bedzie pobierany z JWT
  const user = testCurrentUser;
  const surveys = useGetAllUserSurveys(user.id);
  console.log(surveys.data);

  //TODO display errors
  if (!surveys.isSuccess) return <div>error ladowania</div>;

  return (
    <div>
      <h1 className="mt-24 mb-12 uppercase text-3xl tracking-widest text-center">
        Your surveys
      </h1>
      <div className="flex flex-col space-y-4">
        {surveys.data.map((survey) => (
          <SurveyCard key={survey.id} survey={survey} />
        ))}
      </div>
    </div>
  );
};

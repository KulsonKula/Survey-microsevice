"use client";
import { Link } from "@/modules/ui/Button/Link";
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
    <div className="sm:flex sm:flex-col sm:items-center sm:justify-center space-y-16">
      <h1 className="mt-24 mb-12 uppercase text-3xl tracking-widest text-center">
        Your surveys
      </h1>
      <div className="sm:items-center sm:justify-center flex flex-col sm:flex-row sm:flex-wrap space-y-4 sm:gap-4 sm:space-y-0 sm:w-3/4">
        {surveys.data.map((survey) => (
          <SurveyCard key={survey.id} survey={survey} />
        ))}
        {surveys.data.map((survey) => (
          <SurveyCard key={survey.id} survey={survey} />
        ))}
        {surveys.data.map((survey) => (
          <SurveyCard key={survey.id} survey={survey} />
        ))}
      </div>
      <div className="flex justify-center">
        <Link href="/creator" variant="secondary" size="lg">
          Create a new survey
        </Link>
      </div>
    </div>
  );
};

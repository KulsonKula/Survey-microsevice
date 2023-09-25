import { useQuery } from "@tanstack/react-query";
import { getSurvey } from "./apiUseGetSurvey";

export const useGetSurvey = (accessCode: string) => {
  return useQuery({
    queryKey: ["Surveys", accessCode],
    queryFn: () => getSurvey(accessCode),
  });
};

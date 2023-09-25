import { useQuery } from "@tanstack/react-query";
import { getQuestionsBySurveyId } from "./apiUseGetQuestionsBySurveyId";

export const useGetQuestionsBySurveyId = (id: number) => {
  return useQuery({
    queryKey: ["Questions", id],
    queryFn: () => getQuestionsBySurveyId(id),
  });
};

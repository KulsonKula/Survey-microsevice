import { useQuery } from "@tanstack/react-query";
import { getAllUserSurveys } from "./apiUseGetAllUserSurveys";

export const useGetAllUserSurveys = (userId: number) => {
  return useQuery({
    queryKey: ["Surveys", userId],
    queryFn: () => getAllUserSurveys(userId),
  });
};

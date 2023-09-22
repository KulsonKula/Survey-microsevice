import { api } from "@/services/axios/axiosConfig";
import { useGetAllUserSurveysReturnSchema } from "./schema/useGetAllUserSurveys.schema";

const SURVEY_URL = "/api/user/";

export const getAllUserSurveys = async (userId: number) => {
  const url = SURVEY_URL + String(userId) + "/survey/all";
  const { data } = await api.get(url, {
    params: {
      user_id: userId,
    },
  });
  const surveys = useGetAllUserSurveysReturnSchema.safeParse(data);
  if (surveys.success) {
    return surveys.data;
  }
  throw surveys.error;
};

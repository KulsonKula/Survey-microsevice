import { api } from "@/services/axios/axiosConfig";
import { useGetSurveyReturnSchema } from "./schema/useGetSurvey.schema";

const SURVEY_URL = "/api/survey/";

export const getSurvey = async (accessCode: string) => {
  const url = SURVEY_URL + accessCode;
  const { data } = await api.get(url, {
    params: {
      accessCode,
    },
  });
  console.log(data);
  const survey = useGetSurveyReturnSchema.safeParse(data);
  if (survey.success) {
    return survey.data;
  }
  throw survey.error;
};

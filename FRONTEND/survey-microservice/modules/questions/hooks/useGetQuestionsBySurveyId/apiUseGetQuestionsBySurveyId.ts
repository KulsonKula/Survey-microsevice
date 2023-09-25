import { api } from "@/services/axios/axiosConfig";
import { useGetQuestionsBySurveyIdReturnSchema } from "./schema/useGetQuestionsBySurveyId.schema";

const SURVEY_URL = "/api/survey/";

export const getQuestionsBySurveyId = async (id: number) => {
  const url = SURVEY_URL + String(id) + "/question/all";
  const { data } = await api.get(url, {
    params: {
      id,
    },
  });
  console.log(data);
  const questions = useGetQuestionsBySurveyIdReturnSchema.safeParse(data);
  if (questions.success) {
    return questions.data;
  }
  throw questions.error;
};

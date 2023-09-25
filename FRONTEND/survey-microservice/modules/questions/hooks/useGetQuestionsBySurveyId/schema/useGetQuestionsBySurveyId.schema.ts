import { z } from "zod";

export const useGetQuestionsBySurveyIdReturnSchema = z.array(
  z.object({
    id: z.number(),
    text: z.string(),
    type: z.string(),
    sequence: z.number(),
  })
);

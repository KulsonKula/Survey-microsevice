import { z } from "zod";

export const useGetSurveyReturnSchema = z.object({
  id: z.number(),
  accessCode: z.string(),
  title: z.string(),
  status: z.string(),
  created_at: z.string(),
});

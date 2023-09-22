import { z } from "zod";

export const useGetAllUserSurveysReturnSchema = z.array(
  z.object({
    id: z.number(),
    status: z.string(),
    title: z.string(),
    created_at: z.string(),
  })
);

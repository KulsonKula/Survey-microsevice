import { useMutation } from "@tanstack/react-query";
import { login } from "./apiUseLogin";

type User = {
  username: string;
  password: string;
};

export const useLogin = () => {
  return useMutation({
    mutationFn: ({ username, password }: User) => login({ username, password }),
    onSuccess: () => {
      console.log("success");
    },
    onError: (err) => {
      console.log("ERROR", err);
    },
  });
};

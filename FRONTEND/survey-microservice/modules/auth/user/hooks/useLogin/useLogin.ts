import { useMutation } from "@tanstack/react-query";
import { login } from "./apiUseLogin";
import { useRouter } from "next/navigation";
import { toast } from "react-hot-toast";

type User = {
  username: string;
  password: string;
};

export const useLogin = () => {
  const router = useRouter();

  return useMutation({
    mutationFn: ({ username, password }: User) => login({ username, password }),
    onSuccess: () => {
      router.refresh();
      router.push("/creator");
      toast.success("Successfully logged in");
    },
    onError: (err) => {
      console.log("ERROR", err);
      toast.error("Email and password do not match!");
    },
  });
};

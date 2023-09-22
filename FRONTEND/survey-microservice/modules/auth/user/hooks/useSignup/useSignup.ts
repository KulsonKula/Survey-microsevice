import { useMutation } from "@tanstack/react-query";
import { useRouter } from "next/navigation";
import { toast } from "react-hot-toast";
import { signup } from "./apiUseSignup";

type User = {
  username: string;
  password: string;
  passwordConfirm: string;
};

export const useSignup = () => {
  const router = useRouter();

  return useMutation({
    mutationFn: ({ username, password, passwordConfirm }: User) =>
      signup({ username, password, passwordConfirm }),
    onSuccess: () => {
      router.refresh();
      router.push("/overview");
      toast.success("Successfully created an account");
    },
    onError: (err) => {
      console.log("ERROR", err);
      toast.error("error");
    },
  });
};

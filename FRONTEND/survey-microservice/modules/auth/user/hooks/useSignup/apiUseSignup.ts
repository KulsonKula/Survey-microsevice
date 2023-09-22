import { api } from "@/services/axios/axiosConfig";

type UserSignup = {
  username: string;
  password: string;
  passwordConfirm: string;
};

const SIGNUP_URL = "/api/user/add";

export const signup = async ({
  username,
  password,
  passwordConfirm,
}: UserSignup) => {
  //TODO sprawdzic czy user o takim username juz istnieje

  if (passwordConfirm !== password) return;
  const { data } = await api.put(SIGNUP_URL, {
    username,
    password,
  });
  console.log(data);
  return data;
};

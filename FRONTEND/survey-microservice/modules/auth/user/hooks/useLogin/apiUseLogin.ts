import { api } from "@/services/axios/axiosConfig";

type UserLogin = {
  username: string;
  password: string;
};

const LOGIN_URL = "/api/user/login";

export const login = async ({ username, password }: UserLogin) => {
  const { data } = await api.get(LOGIN_URL, {
    params: {
      username,
      password,
    },
  });
  console.log(data);
  return data;
};

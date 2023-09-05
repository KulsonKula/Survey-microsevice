"use client";
import { Button } from "@/modules/ui/Button/Button";
import { useLogin } from "@/modules/auth/user/hooks/useLogin/useLogin";
import { api } from "@/services/axios/axiosConfig";

const TEST_URL = "/api/test";

export default function TestPage() {
  const login = useLogin();

  const loginAPI = () => {
    login.mutate({ username: "Qlson", password: "polslpolsl" });
  };

  const getAPI = async () => {
    try {
      const res = await api.get(TEST_URL);
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };
  const deleteAPI = async () => {
    try {
      const res = await api.delete(TEST_URL);
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };
  const postAPI = async () => {
    try {
      const res = await api.post(TEST_URL);
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };
  const putAPI = async () => {
    try {
      const res = await api.put(TEST_URL);
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div>
      <Button onClick={getAPI} size="lg">
        TestGET
      </Button>
      <Button onClick={deleteAPI} size="lg">
        TestDELETE
      </Button>
      <Button onClick={postAPI} size="lg">
        TestPOST
      </Button>
      <Button onClick={putAPI} size="lg">
        TestPUT
      </Button>
      <Button onClick={loginAPI} size="lg">
        TestLOGIN
      </Button>
    </div>
  );
}

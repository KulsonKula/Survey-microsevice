"use client";
import { useForm } from "react-hook-form";
import { useLogin } from "../../user/hooks/useLogin/useLogin";
import { FormRow } from "@/modules/ui/Form/FormRow/FormRow";
import { Button } from "@/modules/ui/Button/Button";

export const LoginForm = () => {
  const login = useLogin();

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onSubmit = handleSubmit(({ username, password }) => {
    console.log(username, password);
    login.mutate({ username, password });
  });

  return (
    <>
      <h2 className="text-xl ml-4">Log into an existing account</h2>
      <form onSubmit={onSubmit}>
        <FormRow label="USERNAME" id="username">
          <input
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            type="text"
            id="username"
            defaultValue="Qlson"
            {...register("username", { required: true })}
          />
        </FormRow>
        <FormRow label="PASSWORD" id="password">
          <input
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            type="password"
            id="password"
            defaultValue="polslpolsl"
            {...register("password", { required: true })}
          />
        </FormRow>
        {(errors.password?.message || errors.username?.message) && (
          //tutaj <Text type='error'></Text>

          <span className="text-lg font-semibold bg-red-100 p-1 text-red-700">
            Email or password does not match
          </span>
        )}
        <FormRow>
          <Button disabled={login.isLoading} size="lg" onClick={onSubmit}>
            Log in
          </Button>
        </FormRow>
      </form>
    </>
  );
};

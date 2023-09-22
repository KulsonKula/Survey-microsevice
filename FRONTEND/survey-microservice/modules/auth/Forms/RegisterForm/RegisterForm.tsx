"use client";
import { useForm } from "react-hook-form";

import { FormRow } from "@/modules/ui/Form/FormRow/FormRow";
import { Button } from "../../../ui/Button/Button";
import { useSignup } from "../../user/hooks/useSignup/useSignup";

export function RegisterForm() {
  const signup = useSignup();

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onSubmit = handleSubmit(
    ({ username, passwordSignup, passwordConfirm }) => {
      console.log(username, passwordSignup, passwordConfirm);
      signup.mutate({ username, password: passwordSignup, passwordConfirm });
    }
  );

  return (
    <div>
      <h2 className="text-xl ml-4 sm:ml-0">Create a new account</h2>
      <form onSubmit={onSubmit}>
        <FormRow id="username" label="USERNAME">
          <input
            type="text"
            id="username"
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            {...register("username", {
              required: true,
            })}
          />
          {errors.username?.message && (
            <span className="text-lg font-semibold text-red-700">
              There already is an account with that username
            </span>
          )}
        </FormRow>
        {/* <FormRow id="lastName" label="LAST NAME">
          <input
            type="text"
            id="lastName"
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            {...register("lastName", {
              required: true,
            })}
          />
          {errors.lastName?.message && (
            <span className="text-lg font-semibold text-red-700">
              Please provide a valid last name
            </span>
          )}
        </FormRow>
        <FormRow id="emailSignup" label="EMAIL">
          <input
            type="email"
            id="emailSignup"
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            {...register("emailSignup", { required: true })}
          />
          {errors.emailSignup?.message && (
            <span className="text-lg font-semibold text-red-700">
              Please provide a valid email address
            </span>
          )}
        </FormRow> */}
        <FormRow
          id="passwordSignup"
          label="PASSWORD (min 5, max 30 characters long)"
        >
          <input
            type="password"
            id="passwordSignup"
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            {...register("passwordSignup", {
              required: true,
            })}
          />
          {errors.passwordSignup?.message && (
            <span className="text-lg font-semibold text-red-700">
              Password needs to be at least 5 characters long
            </span>
          )}
        </FormRow>
        <FormRow id="passwordConfirm" label="CONFIRM PASSWORD">
          <input
            type="password"
            id="passwordConfirm"
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            {...register("passwordConfirm", {
              required: true,
            })}
          />
          {errors.passwordConfirm?.message && (
            <span className="text-lg font-semibold text-red-700">
              Passwords need to match
            </span>
          )}
        </FormRow>
        <FormRow>
          <Button variant="primary" size="lg" onClick={onSubmit}>
            Sign up
          </Button>
        </FormRow>
      </form>
    </div>
  );
}

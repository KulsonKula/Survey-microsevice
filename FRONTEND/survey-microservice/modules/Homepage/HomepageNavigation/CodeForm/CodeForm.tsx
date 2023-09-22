"use client";
import { FormRow } from "@/modules/ui/Form/FormRow/FormRow";
import { useRouter } from "next/navigation";
import { useForm } from "react-hook-form";

export const CodeForm = () => {
  const router = useRouter();
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onSubmit = handleSubmit(({ surveyCode }) => {
    router.push(`/survey/${surveyCode}`);
  });

  return (
    <form
      onSubmit={onSubmit}
      className="text-center bg-purple-700 p-1 rounded-md"
    >
      <FormRow label="Or enter a survey code" id="surveyCode">
        <input
          className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
          type="text"
          id="surveyCode"
          {...register("surveyCode", { required: false })}
        />
      </FormRow>
    </form>
  );
};

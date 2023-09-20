"use client";
import { FormRow } from "@/modules/ui/Form/FormRow/FormRow";
import { useForm } from "react-hook-form";

const questionTypes = ["Single-choice", "Multiple-choice", "Text", "Scale"];

export const QuestionCreator = () => {
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onSubmit = handleSubmit(() => {
    console.log("submitted");
  });

  return (
    <>
      <form>
        <FormRow>
          <h2>Select a question type</h2>
          <select
            id="type"
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
          >
            {questionTypes.map((type) => (
              <option key={type} value={type}>
                {type}
              </option>
            ))}
          </select>
        </FormRow>
      </form>
    </>
  );
};

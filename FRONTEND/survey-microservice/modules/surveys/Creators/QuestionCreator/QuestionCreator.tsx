"use client";
import { Button } from "@/modules/ui/Button/Button";
import { FormRow } from "@/modules/ui/Form/FormRow/FormRow";
import { useForm } from "react-hook-form";

const questionTypes = ["Single-choice", "Multiple-choice", "Text", "Scale"];

export const QuestionCreator = () => {
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onSubmit = handleSubmit(({ type, text }) => {
    console.log(type);
    console.log(text);
  });

  const onCancel = () => {
    console.log("tu bedzie modal z ");
  };

  return (
    <>
      <form>
        <FormRow>
          <h2>Select a question type</h2>
          <select
            id="type"
            className="font-semibold rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950"
            {...register("type", { required: true })}
          >
            {questionTypes.map((type) => (
              <option key={type} value={type}>
                {type}
              </option>
            ))}
          </select>
        </FormRow>
        <FormRow>
          <label>Write down the content of the question:</label>
          <textarea
            id="text"
            rows={5}
            className="rounded-md bg-fuchsia-100 px-3 py-1.5 shadow-md text-fuchsia-950 placeholder:text-fuchsia-950"
            placeholder="Example: What are your thoughts on this subject..."
            {...register("text", { required: true })}
          />
        </FormRow>
        <FormRow>
          <div className="flex justify-between first:ml-12 last:mr-12">
            <Button onClick={onSubmit} size="md" variant="primary">
              <h2>Submit question</h2>
            </Button>
            <Button onClick={onCancel} size="md" variant="danger">
              <h2>Cancel</h2>
            </Button>
          </div>
        </FormRow>
      </form>
    </>
  );
};

"use client";
import { Button } from "@/modules/ui/Button/Button";
import { FormRow } from "@/modules/ui/Form/FormRow/FormRow";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { QuestionCard } from "./QuestionCard/QuestionCard";

type QuestionCreatorProps = {
  onSubmitQuestion: () => void;
};

const questionTypes = ["Text", "Multiple-choice", "Single-choice", "Scale"];

export const QuestionCreator = ({ onSubmitQuestion }: QuestionCreatorProps) => {
  const [isEdited, setIsEdited] = useState(true);
  const [typeState, setTypeState] = useState("");
  const [textState, setTextState] = useState("");

  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const onSubmit = handleSubmit(({ type, text }) => {
    console.log(type);
    console.log(text);
    setIsEdited(false);
    setTextState(text);
    setTypeState(type);
    onSubmitQuestion();
  });

  const onCancel = () => {
    console.log("tu bedzie modal z cancelowaniem tworzenia pytania");
  };

  return (
    <>
      {!isEdited ? (
        <QuestionCard
          type={typeState}
          text={textState}
          onEdit={() => setIsEdited(true)}
        />
      ) : (
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
      )}
    </>
  );
};

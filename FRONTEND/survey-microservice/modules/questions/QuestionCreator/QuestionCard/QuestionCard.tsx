import { Button } from "@/modules/ui/Button/Button";

type QuestionCardProps = {
  onEdit: () => void;
  type: string;
  text: string;
};

export const QuestionCard = ({ onEdit, type, text }: QuestionCardProps) => {
  const truncateText = (text: string, maxLength: number) => {
    if (text.length <= maxLength) {
      return text;
    }
    return `${text.substring(0, maxLength)}...`;
  };

  return (
    <div className="bg-fuchsia-100 text-purple-900 font-semibold p-2 flex items-center justify-between sm:mx-4 sm:w-1/3 sm:h-full">
      <span>Type: {type}</span>
      <span>{truncateText(text, 24)}</span>
      <Button variant="secondary" size="sm" onClick={onEdit}>
        <span className="text-purple-900">Overview</span>
      </Button>
    </div>
  );
};

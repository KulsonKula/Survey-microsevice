import { Link } from "@/modules/ui/Button/Link";

type Survey = {
  id: number;
  accessCode: string;
  title: string;
  status: string;
  createdAt: string;
};

type SurveyCardProps = {
  survey: Survey;
};

export const SurveyCard = ({ survey }: SurveyCardProps) => {
  return (
    <div className="bg-fuchsia-100 text-purple-900 font-semibold p-2 items-center flex justify-between sm:mx-4 sm:w-1/3 sm:h-full">
      {survey.title}
      <Link
        href={`/overview/${survey.accessCode}`}
        variant="secondary"
        size="sm"
      >
        <span className="text-purple-900">Overview</span>
      </Link>
    </div>
  );
};

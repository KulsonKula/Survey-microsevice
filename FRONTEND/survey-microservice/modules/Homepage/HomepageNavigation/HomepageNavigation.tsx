import { Link } from "@/modules/ui/Button/Link";
import { CodeForm } from "./CodeForm/CodeForm";

export const HomepageNavigation = () => {
  return (
    <div className="mt-10 flex flex-col sm:flex-row sm:space-x-48 justify-center space-y-10 items-center bg-purple-400 py-16">
      <Link href="/login" variant="primary" size="lg">
        Create a survey
      </Link>
      <CodeForm />
    </div>
  );
};

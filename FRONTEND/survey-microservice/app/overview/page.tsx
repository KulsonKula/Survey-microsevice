import { SurveysOverview } from "@/modules/surveys/SurveysOverview/SurveysOverview";
import { Link } from "@/modules/ui/Button/Link";

export default function OverviewPage() {
  return (
    <main className="flex flex-col space-y-10">
      <SurveysOverview />
      <div className="flex justify-center items-center">
        <Link href="/creator" variant="secondary" size="lg">
          Create a new survey
        </Link>
      </div>
    </main>
  );
}

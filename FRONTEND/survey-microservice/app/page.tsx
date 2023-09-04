import { HomepageNavigation } from "@/modules/Homepage/HomepageNavigation/HomepageNavigation";
import { HomepageTitle } from "@/modules/Homepage/HomepageTitle/HomepageTitle";

export default function Home() {
  return (
    <main>
      <HomepageTitle />
      <HomepageNavigation />
    </main>
  );
}

"use client";
import { Button } from "@/modules/ui/Button/Button";
import { Link } from "@/modules/ui/Button/Link";

export const HomepageTitle = () => {
  const testFunc = () => {
    return;
  };

  return (
    <>
      <Button onClick={testFunc} variant="primary" size="lg">
        Test
      </Button>
      <Button onClick={testFunc} variant="secondary" size="lg">
        Test
      </Button>
      <Button onClick={testFunc} variant="danger" size="lg">
        Test
      </Button>
      <Link href="#">LinkTest</Link>
    </>
  );
};

import { LoginForm } from "@/modules/auth/Forms/LoginForm/LoginForm";
import { RegisterForm } from "@/modules/auth/Forms/RegisterForm/RegisterForm";

export default function LoginPage() {
  return (
    <main className="mt-24">
      <div className="flex flex-col space-y-10">
        {
          //!! tutaj <Text type='header'?></Text>
        }
        <h1 className="text-xl text-center">
          To create your own survey, log into an existing account or create a
          new one
        </h1>
        <LoginForm />
        <RegisterForm />
      </div>
    </main>
  );
}

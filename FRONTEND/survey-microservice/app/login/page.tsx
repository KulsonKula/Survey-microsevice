import { LoginForm } from "@/modules/auth/Forms/LoginForm/LoginForm";
import { RegisterForm } from "@/modules/auth/Forms/RegisterForm/RegisterForm";

export default function LoginPage() {
  return (
    <main className="mt-24">
      <div className="flex flex-col space-y-10">
        {
          //!! tutaj <Text type='header'?></Text>
        }
        <h1 className="text-3xl ml-3 tracking-wider">Start creating surveys</h1>
        <LoginForm />
        <div className="bg-fuchsia-50 h-2"></div>
        <RegisterForm />
      </div>
    </main>
  );
}

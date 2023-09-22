import { LoginForm } from "@/modules/auth/Forms/LoginForm/LoginForm";
import { RegisterForm } from "@/modules/auth/Forms/RegisterForm/RegisterForm";

export default function LoginPage() {
  return (
    <main className="mt-24">
      <h1 className="text-4xl ml-3 my-6 sm:ml-24 tracking-wider">
        Start creating surveys now
      </h1>
      <div className="space-y-12 sm:space-y-0 justify-center sm:mt-36 sm:flex sm:flex-row sm:items-start sm:space-x-12 md:space-x-36 ">
        {
          //!! tutaj <Text type='header'?></Text>
        }
        <LoginForm />
        <div className="sm:hidden bg-fuchsia-50 h-2"></div>
        <RegisterForm />
      </div>
    </main>
  );
}

import { MouseEvent, ReactNode } from "react";
import Link from "next/link";
import clsx from "clsx";

type BaseButtonProps = LinkVariantProps | ButtonVariantProps;

type BaseProps = {
  disabled?: boolean;
  children: ReactNode;
  size?: keyof typeof sizes;
  variant?: string;
  rounded?: boolean;
};

export type LinkVariantProps = {
  as: "a";
  href: string;
  onClick?: (e?: MouseEvent<HTMLAnchorElement>) => void;
} & BaseProps;
export type ButtonVariantProps = {
  as: "button";
  onClick: (e: MouseEvent<HTMLButtonElement>) => void;
} & BaseProps;

type Sizes = {
  sm: string;
  md: string;
  lg: string;
};

type ClassNames = {
  [key: string]: string;
};

const sizes: Sizes = {
  sm: "text-sm px-2.5 py-1.5",
  md: "text-md px-3.5 py-2",
  lg: "text-lg px-6 py-3",
};

const variants: ClassNames = {
  primary: "bg-purple-700 text-purple-200 enabled:hover:bg-purple-600",
  secondary: "bg-purple-300 text-purple-950 enabled:hover:bg-purple-100",
  link: "text-fuchsia-200 bg-transparent enabled:hover:bg-transparent enabled:hover:underline",
  danger: "bg-red-700 text-red-100 enabled:hover:bg-red-600 ",
  accept: "bg-green-200 text-green-600 border-green-400 border",
};
export const BaseButton = (props: BaseButtonProps) => {
  const { variant = "primary", size = "md", rounded = true } = props;
  const className = clsx(
    "disabled:opacity-50 font-semibold transition-all duration-300",
    {
      [variants[variant]]: props.variant,
      [sizes[size]]: props.size,
      "rounded-xl": rounded,
    }
  );
  if (props.as === "a") {
    return (
      <Link href={props.href} className={className}>
        {props.children}
      </Link>
    );
  }
  return (
    <button {...props} className={className}>
      {props.children}
    </button>
  );
};

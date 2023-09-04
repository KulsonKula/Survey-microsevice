type FormRowProps = {
  label?: string;
  children: React.ReactNode;
  id?: string;
};

export function FormRow({ label, children, id }: FormRowProps) {
  return (
    <div className="flex flex-col gap-3 px-0 py-5">
      {label && (
        <label htmlFor={id} className="text-sm tracking-widest">
          {label}
        </label>
      )}
      {children}
    </div>
  );
}

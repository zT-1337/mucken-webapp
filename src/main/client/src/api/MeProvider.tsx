import type { User } from "@/type/user.ts";
import * as React from "react";
import type { FC } from "react";
import { useMe } from "@/api/useMe.tsx";
import { Navigate } from "@tanstack/react-router";

export type MeContext = {
  me: User;
};

const MeContext = React.createContext<MeContext | null>(null);

export const MeProvider: FC<React.PropsWithChildren> = ({ children }) => {
  const { data, isLoading, error } = useMe();

  if (isLoading) {
    return <div>is loading user...</div>;
  }

  if (error) {
    return <Navigate to={"/login"} replace />;
  }

  return <MeContext.Provider value={{ me: data! }}>{children}</MeContext.Provider>;
};

export function useMeContext() {
  const context = React.useContext(MeContext);
  if (!context) {
    throw new Error("useMeContext must be used within an MeProvider");
  }
  return context;
}

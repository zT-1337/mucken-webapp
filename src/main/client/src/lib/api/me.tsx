import { queryOptions, useSuspenseQuery } from "@tanstack/react-query";
import axios from "redaxios";
import type { User } from "@/lib/type/user.ts";
import * as React from "react";
import { apiRoutes } from "@/lib/api/api.routes.ts";

const useMe = () => {
  return useSuspenseQuery(meQueryOptions);
};

const meQueryOptions = queryOptions({
  queryKey: ["me"],
  queryFn: () => fetchMe(),
});

const fetchMe = async () => {
  const user = await axios.get<User>(apiRoutes.me);
  return user.data;
};

export type MeContext = {
  me: User;
};

const MeContext = React.createContext<MeContext | null>(null);

export const MeProvider: React.FC<React.PropsWithChildren> = ({ children }) => {
  const { data } = useMe();

  return <MeContext.Provider value={{ me: data }}>{children}</MeContext.Provider>;
};

export function useMeContext() {
  const context = React.useContext(MeContext);
  if (!context) {
    throw new Error("useMeContext must be used within an MeProvider");
  }
  return context;
}

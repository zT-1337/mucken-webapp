import { queryOptions, useSuspenseQuery } from "@tanstack/react-query";
import axios from "redaxios";
import type { Account } from "@/lib/type/account.ts";
import * as React from "react";
import { apiRoutes } from "@/lib/api/api.routes.ts";
import type { ApiResult } from "@/lib/type/util.ts";

const useMe = () => {
  return useSuspenseQuery(meQueryOptions);
};

const meQueryOptions = queryOptions({
  queryKey: ["me"],
  queryFn: () => fetchMe(),
});

const fetchMe = async () => {
  const user = await axios.get<ApiResult<Account, "AccountNotFound">>(apiRoutes.me);
  return user.data;
};

export type MeContext = {
  me: Account;
};

const MeContext = React.createContext<MeContext | null>(null);

export const MeProvider: React.FC<React.PropsWithChildren> = ({ children }) => {
  const { data } = useMe();

  if (!data.ok) {
    throw new Error("User could not be authenticated");
  }

  return <MeContext.Provider value={{ me: data.ok }}>{children}</MeContext.Provider>;
};

export function useMeContext() {
  const context = React.useContext(MeContext);
  if (!context) {
    throw new Error("useMeContext must be used within an MeProvider");
  }
  return context;
}

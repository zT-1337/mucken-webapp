import { queryOptions, useQuery } from "@tanstack/react-query";
import axios from "redaxios";
import type { User } from "@/type/user.ts";

export const useMe = () => {
  const meQuery = useQuery(meQueryOptions);

  return meQuery;
};

const meQueryOptions = queryOptions({
  queryKey: ["me"],
  queryFn: () => fetchMe(),
});

const fetchMe = async () => {
  const user = await axios.get<User>("/api/v1/users/read-me");
  return user.data;
};

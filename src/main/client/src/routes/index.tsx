import { createFileRoute, Link } from "@tanstack/react-router";
import { Button } from "@/components/ui/button.tsx";
import { useEffect, useState } from "react";

export const Route = createFileRoute("/")({
  component: Index,
});

function Index() {
  const [user, setUser] = useState<{ name: string; email: string } | null>(null);

  useEffect(() => {
    fetch("/api/v1/users/read-me")
      .then((result) => result.json())
      .then((result) => {
        // eslint-disable-next-line @typescript-eslint/no-unsafe-argument
        setUser(result);
      })
      .catch((error: unknown) => {
        console.error(error);
      });
  }, []);

  return (
    <>
      <div>Hello "/@index"!</div>
      <Link to={"/about"}>About me, bro</Link>
      <Button
        onClick={() => {
          console.log("Hello");
        }}
      >
        Click Me
      </Button>
      <div>
        {user?.name} {user?.email}
      </div>
    </>
  );
}

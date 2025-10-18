import { createFileRoute, Link } from "@tanstack/react-router";
import { Button } from "@/components/ui/button.tsx";
import React from "react";

export const Route = createFileRoute("/_auth/")({
  component: _authIndex,
});

function _authIndex() {
  return (
    <section className={"space-y-4"}>
      <MainMenuButton to={"/game/local/create"}>Local Game</MainMenuButton>
      <MainMenuButton to={"/profile"}>Profile</MainMenuButton>
      <MainMenuButton to={"/logout"}>Logout</MainMenuButton>
    </section>
  );
}

function MainMenuButton({ to, children }: { to: string; children: React.ReactNode }) {
  return (
    <Button className={"w-full"} asChild>
      {to !== "/logout" ? <Link to={to}>{children}</Link> : <a href={to}>{children}</a>}
    </Button>
  );
}

import { Outlet, createRootRoute } from "@tanstack/react-router";

export const Route = createRootRoute({
  component: RootComponent,
});

function RootComponent() {
  return (
    <div className={"h-screen pt-4"}>
      <main className={"container mx-auto border-2 p-4 shadow bg-background"}>
        <h1 className={"text-4xl text-center my-4"}>Auf einen Muck?</h1>
        <Outlet />
      </main>
    </div>
  );
}

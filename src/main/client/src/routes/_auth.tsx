import { createFileRoute, Outlet } from "@tanstack/react-router";
import { MeProvider } from "@/api/MeProvider.tsx";

export const Route = createFileRoute("/_auth")({
  component: AuthLayout,
});

function AuthLayout() {
  return (
    <MeProvider>
      <Outlet />
    </MeProvider>
  );
}

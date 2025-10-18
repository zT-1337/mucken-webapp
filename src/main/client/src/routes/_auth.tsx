import { createFileRoute, Navigate, Outlet } from "@tanstack/react-router";
import { MeProvider } from "@/api/me.tsx";
import React from "react";
import { Spinner } from "@/components/ui/spinner.tsx";
import { ErrorBoundary } from "react-error-boundary";

export const Route = createFileRoute("/_auth")({
  component: AuthLayout,
});

function AuthLayout() {
  return (
    <ErrorBoundary fallback={<Navigate to={"/login"} />}>
      <React.Suspense fallback={<Spinner />}>
        <MeProvider>
          <Outlet />
        </MeProvider>
      </React.Suspense>
    </ErrorBoundary>
  );
}

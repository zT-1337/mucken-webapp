import { createFileRoute, Navigate, Outlet } from "@tanstack/react-router";
import { MeProvider } from "@/lib/api/me.tsx";
import React from "react";
import { Spinner } from "@/components/ui/spinner.tsx";
import { ErrorBoundary } from "react-error-boundary";
import { QueryErrorResetBoundary } from "@tanstack/react-query";

export const Route = createFileRoute("/_auth")({
  component: AuthLayout,
});

function AuthLayout() {
  return (
    <ErrorBoundary fallback={<Navigate to={"/login"} />}>
      <React.Suspense fallback={<Spinner className={"size-32 mx-auto p-4"} />}>
        <MeProvider>
          <QueryErrorResetBoundary>
            <Outlet />
          </QueryErrorResetBoundary>
        </MeProvider>
      </React.Suspense>
    </ErrorBoundary>
  );
}

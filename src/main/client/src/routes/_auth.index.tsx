import { createFileRoute } from "@tanstack/react-router";
import { Button } from "@/components/ui/button.tsx";

export const Route = createFileRoute("/_auth/")({
  component: _authIndex,
});

function _authIndex() {
  return (
    <>
      <Button
        onClick={() => {
          console.log("Hello");
        }}
      >
        Local Game
      </Button>
    </>
  );
}

import { createFileRoute, Link } from "@tanstack/react-router";
import { Button } from "@/components/ui/button.tsx";

export const Route = createFileRoute("/")({
  component: Index,
});

function Index() {
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
    </>
  );
}

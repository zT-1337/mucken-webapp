import { createFileRoute, Link } from "@tanstack/react-router";
import { Button } from "@/components/ui/button.tsx";
import { useMeContext } from "@/api/MeProvider.tsx";

export const Route = createFileRoute("/_auth/")({
  component: _authIndex,
});

function _authIndex() {
  const { me } = useMeContext();

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
        {me.name} {me.email}
      </div>
    </>
  );
}

import { createFileRoute, Link } from "@tanstack/react-router";

export const Route = createFileRoute("/")({
  component: Index,
});

function Index() {
  return (
    <>
      <div>Hello "/@index"!</div>
      <Link to={"/about"}>About me, bro</Link>
    </>
  );
}

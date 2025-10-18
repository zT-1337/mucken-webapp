import { createFileRoute } from "@tanstack/react-router";

export const Route = createFileRoute("/login")({
  component: Login,
});

function Login() {
  return <a href="http://localhost:8080/oauth2/authorization/google">Google Login</a>;
}

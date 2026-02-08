import { useState } from "react";
import { useClientes } from "../hooks/useClientes";

export function ClientesPage() {
  const { clientes, isLoading, error, salvar } = useClientes();

  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");

  async function onSubmit(e: React.SyntheticEvent<HTMLFormElement>) {
    e.preventDefault();

    await salvar({
      nome,
      email,
      status: "ATIVO",
    });

    setNome("");
    setEmail("");
  }

  return (
    <div style={{ padding: 24 }}>
      <h1>Clientes</h1>

      <form onSubmit={onSubmit}>
        <input
          placeholder="Nome"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
        />
        <input
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <button type="submit">Salvar</button>
      </form>

      <hr />

      {isLoading && <p style={{ color: "white" }}>Carregando clientes...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      <ul>
        {clientes.map((c) => (
          <li key={c.id}>
            {c.nome} - {c.email} - {c.status}
          </li>
        ))}
      </ul>
    </div>
  );
}

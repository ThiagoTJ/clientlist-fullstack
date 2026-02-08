import { render, screen, waitFor } from "@testing-library/react";
import { describe, it, expect, vi, beforeEach } from "vitest";
import { ClientesPage } from "./ClientesPage";
import * as api from "../api/clientesApi";

vi.mock("../api/clientesApi");

describe("ClientesPage", () => {
  beforeEach(() => {
    vi.restoreAllMocks();
  });

  it("deve renderizar clientes vindos da API", async () => {
    vi.spyOn(api, "listarClientes").mockResolvedValue([
      { id: 1, nome: "João", email: "joao@email.com", status: "ATIVO" },
    ]);

    render(<ClientesPage />);

    expect(screen.getByText("Carregando clientes...")).toBeInTheDocument();
    expect(
      await screen.findByText("João - joao@email.com - ATIVO"),
    ).toBeInTheDocument();
  });

  it("deve mostrar mensagem de erro quando a API falhar", async () => {
    vi.spyOn(api, "listarClientes").mockRejectedValue(new Error("erro"));

    render(<ClientesPage />);

    expect(
      await screen.findByText("Erro inesperado ao carregar clientes"),
    ).toBeInTheDocument();
  });

  it("mostra loading enquanto carrega os clientes", async () => {
    vi.spyOn(api, "listarClientes").mockImplementation(
      () =>
        new Promise((resolve) => {
          setTimeout(() => {
            resolve([]);
          }, 100);
        }),
    );

    render(<ClientesPage />);

    expect(screen.getByText(/carregando clientes/i)).toBeInTheDocument();

    await waitFor(() => expect(api.listarClientes).toHaveBeenCalled());
  });
});

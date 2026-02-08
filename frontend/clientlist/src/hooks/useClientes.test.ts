import { renderHook, waitFor } from "@testing-library/react";
import { useClientes } from "./useClientes";
import type { Cliente } from "../types/Cliente";
import * as api from "../api/clientesApi";
import { beforeEach, describe, expect, it, vi } from "vitest";

vi.mock("../api/clientesApi");

describe("useClientes", () => {
  beforeEach(() => {
    vi.resetAllMocks();
  });

  it("deve carregar a lista de clientes", async () => {
    const clientesMock: Cliente[] = [
      { id: 1, nome: "Ana", email: "ana@email.com", status: "ATIVO" },
    ];

    vi.spyOn(api, "listarClientes").mockResolvedValue(clientesMock);

    const { result } = renderHook(() => useClientes());

    await waitFor(() => {
      expect(result.current.clientes.length).toBe(1);
    });

    expect(result.current.isLoading).toBe(false);
    expect(result.current);
  });

  it("deve expor erro ao falhar no carregamento", async () => {
    vi.spyOn(api, "listarClientes").mockRejectedValue(new Error("falha"));

    const { result } = renderHook(() => useClientes());

    await waitFor(() => {
      expect(result.current.error).toBeTruthy();
    });

    expect(result.current.isLoading).toBe(false);
  });

  it("deve salvar e recarregar a lista", async () => {
    const clientesMock: Cliente[] = [
      { id: 1, nome: "Ana", email: "ana@email.com", status: "ATIVO" },
    ];

    const listarSpy = vi
      .spyOn(api, "listarClientes")
      .mockRejectedValue(clientesMock);

    const criarSpy = vi
      .spyOn(api, "criarCliente")
      .mockResolvedValue(undefined as never)

    const { result } = renderHook(() => useClientes())

    await waitFor(() => {
      expect(listarSpy).toHaveBeenCalled()
    })

    await result.current.salvar({
      nome: "Ana",
      email: "ana@email.com",
      status: "ATIVO",
    })

    await waitFor(() => {
      expect(criarSpy).toHaveBeenCalled()
    })

    expect(listarSpy).toHaveBeenCalledTimes(2)
  });
});

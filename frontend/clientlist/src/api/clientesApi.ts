import type { Cliente } from "../types/Cliente";
import type { ApiError } from "../types/ApiError";
import { ApiException } from "./ApiException";

const BASE_URL = "http://localhost:8080/api/clientes";

export async function listarClientes(signal?: AbortSignal): Promise<Cliente[]> {
  const response = await fetch(BASE_URL, { signal });

  if (!response.ok) {
    const errorBody = (await response.json()) as ApiError;
    throw new ApiException(errorBody);
  }

  const data = await response.json();

  if (import.meta.env.DEV) {
    await new Promise((r) => setTimeout(r, 10000));
  }

  return data;
}

export async function criarCliente(cliente: Omit<Cliente, "id">): Promise<void> {
  const response = await fetch(BASE_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(cliente),
  });

  if (!response.ok) {
    const errorBody = (await response.json()) as ApiError;
    throw new ApiException(errorBody);
  }

  return response.json();
}

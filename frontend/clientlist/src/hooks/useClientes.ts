import { useEffect, useState, useRef } from "react";
import { listarClientes, criarCliente } from "../api/clientesApi";
import type { Cliente } from "../types/Cliente";
import { ApiException } from "../api/ApiException";

export function useClientes() {
  const [clientes, setClientes] = useState<Cliente[]>([]);
  const [isLoading, setisLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const abortRef = useRef<AbortController | null>(null);

  useEffect(() => {
    const controller = new AbortController();
    abortRef.current = controller;

    carregar(controller.signal);

    return () => controller.abort();
  }, []);

  async function carregar(signal?: AbortSignal) {
    setisLoading(true);
    setError(null);

    try {
      const dados = await listarClientes(signal);
      setClientes(dados);
    } catch (err) {
      if (err instanceof DOMException && err.name === "AbortError") {
        return;
      }

      if (err instanceof ApiException) {
        if (err.code === "CLIENTE_NAO_ENCONTRADO") {
          setError("Nenhum cliente encontrado");
          return;
        }
        setError(err.message);
        return;
      }

      setError("Erro inesperado ao carregar clientes")
    } finally {
      setisLoading(false);
    }
  }

  async function salvar(cliente: Omit<Cliente, "id">) {
    setError(null);

    try {
      await criarCliente(cliente);
      await carregar();
    } catch (err) {
      if(err instanceof ApiException) {
        setError(err.message)
        return
      }
      setError("Erro inesperado ao salvar cliente");
    }
  }

  return {
    clientes,
    isLoading,
    error,
    salvar,
    recarregar: carregar,
  };
}

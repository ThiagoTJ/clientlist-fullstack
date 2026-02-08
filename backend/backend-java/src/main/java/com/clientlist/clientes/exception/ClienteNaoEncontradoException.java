package com.clientlist.clientes.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

  public ClienteNaoEncontradoException(Long id) {
    super("Cliente não encontrado: " + id);
  }

}

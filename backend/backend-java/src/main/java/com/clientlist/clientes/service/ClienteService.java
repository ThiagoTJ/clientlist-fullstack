package com.clientlist.clientes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clientlist.clientes.dto.ClienteDTO;
import com.clientlist.clientes.exception.ClienteNaoEncontradoException;
import com.clientlist.clientes.repository.ClienteRepository;

@Service
public class ClienteService {

  private final ClienteRepository repository;

  public ClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

  public List<ClienteDTO> listar() {
    return repository.findAll();
  }

  public ClienteDTO buscar(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new ClienteNaoEncontradoException(id));
  }

  public ClienteDTO salvar(ClienteDTO dto) {
    return repository.save(dto);
  }
}

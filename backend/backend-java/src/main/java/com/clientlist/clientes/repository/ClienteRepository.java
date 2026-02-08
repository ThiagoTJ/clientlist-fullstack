package com.clientlist.clientes.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.clientlist.clientes.dto.ClienteDTO;

@Repository
public class ClienteRepository {

  private final List<ClienteDTO> base = new ArrayList<>();

  public ClienteRepository() {
    base.add(new ClienteDTO(1L, "Maria Silva", "maria@email.com", "ATIVO"));
    base.add(new ClienteDTO(2L, "João Souza", "joao@email.com", "INATIVO"));
    base.add(new ClienteDTO(3L, "Ana Lima", "ana@email.com", "ATIVO"));
    base.add(new ClienteDTO(4L, "Carlos Rocha", "carlos@email.com", "ATIVO"));
  }

  public List<ClienteDTO> findAll() {
    return base;
  }

  public Optional<ClienteDTO> findById(Long id) {
    return base.stream()
      .filter(c -> c.getId().equals(id))
      .findFirst();
  }

  public ClienteDTO save(ClienteDTO dto) {
    base.removeIf(c -> c.getId().equals(dto.getId()));
    base.add(dto);
    return dto;
  }
}

package com.clientlist.clientes.mapper;

import org.springframework.stereotype.Component;

import com.clientlist.clientes.dto.ClienteDTO;
import com.clientlist.clientes.entity.Cliente;

@Component
public class ClienteMapper {

  public ClienteDTO toDTO(Cliente entity) {
    if (entity == null) return null;

    ClienteDTO dto = new ClienteDTO();
    dto.setId(entity.getId());
    dto.setNome(entity.getNome());
    dto.setEmail(entity.getEmail());
    dto.setStatus(entity.getStatus());
    
    return dto;
  }

  public Cliente toEntity(ClienteDTO dto) {
    if (dto == null) return null;

    Cliente entity = new Cliente();
    entity.setId(dto.getId());
    entity.setNome(dto.getNome());
    entity.setEmail(dto.getEmail());
    entity.setStatus(dto.getStatus());

    return entity;
  }
}

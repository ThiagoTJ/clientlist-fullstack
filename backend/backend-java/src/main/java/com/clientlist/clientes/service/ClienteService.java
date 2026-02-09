package com.clientlist.clientes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clientlist.clientes.dto.ClienteDTO;
import com.clientlist.clientes.entity.Cliente;
import com.clientlist.clientes.exception.ClienteNaoEncontradoException;
import com.clientlist.clientes.mapper.ClienteMapper;
import com.clientlist.clientes.repository.ClienteJpaRepository;

@Service
public class ClienteService {

  private final ClienteJpaRepository repository;
  private final ClienteMapper mapper;

  public ClienteService(ClienteJpaRepository repository, ClienteMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public Page<ClienteDTO> listar(Pageable pageable) {
    return repository.findAll(pageable)
      .map(mapper::toDTO);
  }

  public ClienteDTO buscar(Long id) {
    Cliente cliente = repository.findById(id)
      .orElseThrow(() -> new ClienteNaoEncontradoException(id));

      return mapper.toDTO(cliente);
  }

  public ClienteDTO salvar(ClienteDTO dto) {
    Cliente entity = mapper.toEntity(dto);
    Cliente salvo = repository.save(entity);

    return mapper.toDTO(salvo);
  }
}

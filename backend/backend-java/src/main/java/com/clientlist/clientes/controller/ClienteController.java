package com.clientlist.clientes.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientlist.clientes.dto.ClienteDTO;
import com.clientlist.clientes.service.ClienteService;


@RestController
@RequestMapping("api/clientes")
@CrossOrigin
public class ClienteController {

  private final ClienteService service;

  public ClienteController(ClienteService service) {
    this.service = service;
  }

  @GetMapping
  public Page<ClienteDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
    return service.listar(pageable);
  }

  @GetMapping("/{id}")
  public ClienteDTO buscar(@PathVariable Long id) {
    return service.buscar(id);
  }

  @PostMapping
  public ClienteDTO criar(@Valid @RequestBody ClienteDTO dto) {
    return service.salvar(dto);
  }

  @PutMapping("/{id}")
  public ClienteDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
    dto.setId(id);
    return service.salvar(dto);
  }
}

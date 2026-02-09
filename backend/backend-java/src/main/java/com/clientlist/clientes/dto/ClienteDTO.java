package com.clientlist.clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {
  private Long id;

  @NotBlank(message = "Nome é obrigatório")
  private String nome;

  @NotBlank(message = "Email é obrigatório")
  @Email(message = "Email inválido")
  private String email;

  @NotBlank(message = "Status é obrigatório")
  private String status;

  public ClienteDTO() {}

  public ClienteDTO(Long id, String nome, String email, String status) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.status = status;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}

package com.clientlist.clientes.dto;

public class ClienteDTO {
  private Long id;
  private String nome;
  private String email;
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

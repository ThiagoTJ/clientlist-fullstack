package com.clientlist.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientlist.clientes.entity.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Long>{
}

package com.init.clientetc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.clientetc.entidad.Cliente;

public interface ClientetcDAO extends JpaRepository<Cliente, Long> {

}

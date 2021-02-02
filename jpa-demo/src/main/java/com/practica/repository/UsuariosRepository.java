package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.model.Usuario;


public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}

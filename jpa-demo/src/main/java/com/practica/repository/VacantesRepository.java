package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer > {

}

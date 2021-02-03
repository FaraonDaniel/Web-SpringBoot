package com.practica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer > {

	List<Vacante> findByEstatus(String estatus);
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(Double salarioMin, Double salarioMax);
	List<Vacante> findByEstatusIn(String[] estatus);
	
}

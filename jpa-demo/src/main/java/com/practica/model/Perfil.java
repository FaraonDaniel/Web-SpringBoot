package com.practica.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity 
@Table(name="Perfiles")
public class Perfil {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String perfil;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="UsuarioPerfil",
			joinColumns = @JoinColumn(name="idPerfil"),
			inverseJoinColumns = @JoinColumn(name="idUsuario")
			)
	private List<Perfil> usuarios;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public List<Perfil> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Perfil> usuarios) {
		this.usuarios = usuarios;
	}
	@Override
	public String toString() {
		return "Perfil [id=" + id + ", perfil=" + perfil + ", usuarios=" + usuarios + "]";
	}
	

	
}

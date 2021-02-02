package com.practica;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.practica.model.Categoria;
import com.practica.model.Vacante;
import com.practica.repository.CategoriasRepository;
import com.practica.repository.PerfilesRepository;
import com.practica.repository.UsuariosRepository;
import com.practica.repository.VacantesRepository;


@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Autowired
	private VacantesRepository repoVacantes;
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	@Autowired
	private PerfilesRepository repoPerfiles;
	
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(repoCategorias);
	}
	
	private void guardarCategoria(Categoria categoria) {
		repoCategorias.save(categoria);

	}
	
	private void eliminarCategoria(Categoria categoria) {
		repoCategorias.delete(categoria);
	}
	
	private void eliminarTodosCategoria() {
		repoCategorias.deleteAllInBatch();
	}
	
	private Categoria buscarPorIdCategoria(Integer id) {
		return repoCategorias.findById(id).get();
	}
	
	private List<Categoria> buscarTodosCategoria() {
		return repoCategorias.findAll();
	}
	
	private List<Categoria> buscarTodosPaginacionCategoria() {
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5)); //pagina 0-> del 1 al 5, pagina 1-> del 6 al 10, pagina 2-> del 11 al 15
		return page.getContent();
	}
	
	private List<Categoria> buscarTodosOrdenadosNombreDescendienteCategoria() {
		return repoCategorias.findAll(Sort.by("nombre").descending());
	}
	
	private List<Categoria> encontrarPorIdsCategoria(List<Integer> ids) {
		return repoCategorias.findAllById(ids);
	}
	
	
	private boolean actualizarCategoria(Categoria categoria) {
		//TODO change to JPARepository mode
		
		Optional<Categoria> optional = repoCategorias.findById(categoria.getId());
		if(optional.isPresent()) {
			Categoria oldCategoria = optional.get();
			oldCategoria.setDescripcion(categoria.getDescripcion());
			oldCategoria.setNombre(categoria.getNombre());
			repoCategorias.save(oldCategoria);
			return true;
		}
		else {
			//repo.save(categoria);
			System.out.println("La categoria no esta");
			return false;
		
		}

	}
	private Long tamañoTablaCategoria() {
		return repoCategorias.count();
	}
	
	private boolean existeIdCategoria(int id) {
		return repoCategorias.existsById(id);
	}
	
	private boolean saveAllCategoria(List<Categoria> categoria) {
		//en vez de pasar por parametro, recoger todas de la clase
		return (repoCategorias.saveAll(categoria)!=null)?true:false;
		
	}
	
	
	//Vacantes
	
	private List<Vacante> buscarTodasVacantes() {
		return repoVacantes.findAll();
	}
	
	private void guardarVacante(Vacante vacante) {
		repoVacantes.save(vacante);
		System.out.println(vacante);
	}
	
	

}

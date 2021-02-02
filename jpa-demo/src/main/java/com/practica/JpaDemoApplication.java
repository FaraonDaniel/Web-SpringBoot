package com.practica;

import java.util.LinkedList;
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
import com.practica.repository.CategoriasRepository;


@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(repo);
	}
	
	private void guardar() {
		Categoria cat=new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas");
		repo.save(cat);
		System.out.println(cat);
	}
	
	private void eliminar(Categoria categoria) {
		repo.delete(categoria);
	}
	
	private void eliminarTodos() {
		repo.deleteAllInBatch();
	}
	
	private Categoria buscarPorId(Integer id) {
		return repo.findById(id).get();
	}
	
	private List<Categoria> buscarTodos() {
		return repo.findAll();
	}
	
	private List<Categoria> buscarTodosPaginacion() {
		Page<Categoria> page = repo.findAll(PageRequest.of(0, 5)); //pagina 0-> del 1 al 5, pagina 1-> del 6 al 10, pagina 2-> del 11 al 15
		return page.getContent();
	}
	
	private List<Categoria> buscarTodosOrdenadosNombreDescendiente() {
		return repo.findAll(Sort.by("nombre").descending());
	}
	
	private List<Categoria> encontrarPorIds(List<Integer> ids) {
		return repo.findAllById(ids);
	}
	
	
	private boolean actualizarCategoria(Categoria categoria) {
		
		Optional<Categoria> optional = repo.findById(categoria.getId());
		if(optional.isPresent()) {
			Categoria oldCategoria = optional.get();
			oldCategoria.setDescripcion(categoria.getDescripcion());
			oldCategoria.setNombre(categoria.getNombre());
			repo.save(oldCategoria);
			return true;
		}
		else {
			//repo.save(categoria);
			System.out.println("La categoria no esta");
			return false;
		
		}

	}
	private Long tamañoTabla() {
		return repo.count();
	}
	
	private boolean existeId(int id) {
		return repo.existsById(id);
	}
	
	private boolean saveAll(List<Categoria> categoria) {
		//en vez de pasar por parametro, recoger todas de la clse
		return (repo.saveAll(categoria)!=null)?true:false;
		
	}

}

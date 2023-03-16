package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Categoria;

public interface CategoriaRepository extends CrudRepository <Categoria, Long> {
	
	/**
	 * Busca y recupera una Categoria con el nombre
	 */
	public Categoria findByNombre(String nombre);
}

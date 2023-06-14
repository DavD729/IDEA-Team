package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

public interface ProductoRepository extends CrudRepository <Producto, Long> {
	
	/**
	 * Busca un producto usando su Nombre
	 * 
	 * @param Nombre
	 * @return Regresa el producto encontrado
	 */
	public List<Producto> findByNombre(String nombre);
	
	/**
	 * Busca todos los productos asociados a una categoria
	 * @param Categoria
	 * @return Colección de todos los productos asociados, la colección puede estar vacia si la categoria no existe,
	 * o bien, no existe ningun producto asociado a ella.
	 */
	public List<Producto> findByCategoria(String categoria);
}
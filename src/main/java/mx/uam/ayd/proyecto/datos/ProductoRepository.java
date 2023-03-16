package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

public interface ProductoRepository extends CrudRepository <Producto, Long> {
	
	/**
	 * Busca un producto usando su Nombre
	 * 
	 * @param Nombre
	 * @return Regresa el producto encontrado
	 */
	public Producto findByNombre(String nombre);
	
	/**
	 * Busca todos los productos asociados a una categoria
	 * @param Categoria
	 * @return Colección de todos los productos asociados, la colección puede estar vacia si la categoria no existe,
	 * o bien, no existe ningun producto asociado a ella.
	 */
	@Query("SELECT c FROM Producto c WHERE c.categoria = :categoria")
	public List<Producto> findAllByCategoria(@Param("categoria") String categoria);
}
package mx.uam.ayd.proyecto.datos;


import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Producto1;


public interface Producto1Repository extends CrudRepository <Producto1, Long>{
	
	public Producto1 findByidProducto(long idProducto);
	@SuppressWarnings("unchecked")
	public Producto1 save(Producto1 p);
	public LinkedList<Producto1> findAll();
	public LinkedList<Producto1> findByNombre(String nombre);
	public void delete(Producto1 p);
}
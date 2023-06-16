package mx.uam.ayd.proyecto.datos;
import java.util.LinkedList;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.OrdenProducto;


public interface OrdenProductoRepository extends CrudRepository <OrdenProducto, Long>{
	public LinkedList<OrdenProducto> findAll();
	public OrdenProducto findByidOrdenProducto(long idOrdenProducto);
}

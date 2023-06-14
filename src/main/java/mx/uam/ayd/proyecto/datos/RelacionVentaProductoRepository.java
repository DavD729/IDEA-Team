package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.RelacionProductoValor;

public interface RelacionVentaProductoRepository extends CrudRepository <RelacionProductoValor, Long> {
	
	/**
	 * Busca ventas de acuerdo a los Productos vendidos
	 * @param fecha Fecha del historial a buscar
	 * @return Historial de la fecha indicada
	 */
	public List<RelacionProductoValor> findByProducto(long idProducto);
	
	public List<RelacionProductoValor> findByRelacion(long relacion);
}

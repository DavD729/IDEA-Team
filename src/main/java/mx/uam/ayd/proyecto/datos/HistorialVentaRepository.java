package mx.uam.ayd.proyecto.datos;

import java.time.YearMonth;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.HistorialVenta;

public interface HistorialVentaRepository extends CrudRepository <HistorialVenta, Long> {
	
	/**
	 * Busca un historial de acuerdo a la fecha proveída
	 * @param fecha Fecha del historial a buscar
	 * @return Historial de la fecha indicada
	 */
	public HistorialVenta findByFecha(YearMonth fecha);
}

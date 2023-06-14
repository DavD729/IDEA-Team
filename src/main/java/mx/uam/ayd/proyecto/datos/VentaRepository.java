package mx.uam.ayd.proyecto.datos;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Repository
public interface VentaRepository extends CrudRepository <Venta, Long>{
	/**
	* Busca las ventas del dia por medio de la fecha actual del sistema, las guarda en una Lista y devuelve la Lista
	* 
	* @param LocalDate fecha: Fecha actual del sistema
	* 
	* @return List<Venta>: Lista de ventas del dia 
	**/
	public List<Venta> findByDate(LocalDate fecha);
}
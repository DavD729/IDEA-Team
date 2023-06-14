package mx.uam.ayd.proyecto.datos;

import java.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.uam.ayd.proyecto.negocio.modelo.Corte;

@Repository
public interface CorteRepository extends CrudRepository <Corte, Long>{
	/**
	* Busca un Corte de Caja por medio de la fecha en el seleccionador de fecha y el tipo de operacion (1= ingresos, 2 = egresos)
	* 	si dicho Corte existe lo regresa con sus parametros para ser mostrado en la tabla, sino existe regresa null
	*
	* @param LocalDate fecha: Fecha de la cual se desea recuperar el corte
	* 		int tipoOperacion: tipo de Corte (Engresos = 1, Egresos = 2)
	* 
	* @return Corte: Un nuevo objeto que representa el corte del dia solicitado
	**/
	public Corte findByDateAndTipoOperacion (LocalDate fecha, int tipoOperacion);
}

package mx.uam.ayd.proyecto.datos;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uam.ayd.proyecto.negocio.modelo.Corte;

@Repository
public interface CorteRepository extends CrudRepository <Corte, Long>{
	/*
	 * Busca un corte de caja por medio de la fecha
	 */
	public Corte findByDate (LocalDate fecha);
	
	public Corte findByDateAndTipoOperacion (LocalDate fecha, int tipoOperacion);
	
}

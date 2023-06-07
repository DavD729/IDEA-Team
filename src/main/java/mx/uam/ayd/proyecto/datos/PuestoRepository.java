package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Puesto;

public interface PuestoRepository extends CrudRepository <Puesto,Long> {
	//Consulta y recupera los datos por como fue nombrado el Puesto
	public Puesto findByNombre(String nombre);
	
	
}

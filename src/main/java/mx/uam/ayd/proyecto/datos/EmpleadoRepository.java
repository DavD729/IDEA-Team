package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

public interface EmpleadoRepository extends CrudRepository <Empleado,Long> {
	//Consulta y recupera los datos por email
	public Empleado findByEmail(String email);
	

}

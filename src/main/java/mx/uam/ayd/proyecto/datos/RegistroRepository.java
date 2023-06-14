package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;


import mx.uam.ayd.proyecto.negocio.modelo.Registro;
public interface RegistroRepository extends CrudRepository <Registro,Long>{
	/*Consulta y recupera los datos por como fue nombrado el Registro*/
	public Registro findByNombre(String nombre);
}

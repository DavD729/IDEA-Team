package mx.uam.ayd.proyecto.datos;



import org.springframework.data.repository.CrudRepository;


import mx.uam.ayd.proyecto.negocio.modelo.Tiempo;

public interface TiempoRepository extends CrudRepository <Tiempo,Long> {
	
	/*Consulta y recupera los datos registrados al dia*/
	public Tiempo findByDia(String Dia);
}

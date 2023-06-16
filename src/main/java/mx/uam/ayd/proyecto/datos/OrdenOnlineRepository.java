package mx.uam.ayd.proyecto.datos;

import java.sql.Timestamp;
import java.util.LinkedList;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.OrdenOnline;

public interface OrdenOnlineRepository extends CrudRepository <OrdenOnline, Long> {
	
	public OrdenOnline findByidOrdenOnline(long idOrdenOnline);
	public OrdenOnline findByhoraApertura(Timestamp horaApertura);
	@SuppressWarnings("unchecked")
	public OrdenOnline save(OrdenOnline o);
	public LinkedList<OrdenOnline> findAll();
	public LinkedList<OrdenOnline> findByHoraApertura(Timestamp horaApertura);
	public LinkedList<OrdenOnline> findByHoraCierre(Timestamp horaCierre);
	public LinkedList<OrdenOnline> findByEstado(int estado);
	public void delete(OrdenOnline orden);
}

package mx.uam.ayd.proyecto.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.datos.OrdenOnlineRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.OrdenOnline;
import mx.uam.ayd.proyecto.negocio.modelo.OrdenProducto;

@Service
public class ServicioOrdenOnline {
	
	@Autowired 
	private OrdenOnlineRepository ordenOnlineRepository;
	
	@Autowired 
	private EmpleadoRepository clienteRepository;

	
	public OrdenOnline agregaOrdenOnline(Timestamp horaApertura, float total, long idCliente) {
		
		Empleado cliente = clienteRepository.findById(idCliente).get();
		
		if(cliente == null) {
			throw new IllegalArgumentException("No se encontró el empleado con id "+idCliente);
		}
		
		OrdenOnline o = new OrdenOnline(1,horaApertura,total);
		OrdenOnline retorno = ordenOnlineRepository.save(o);

		clienteRepository.save(cliente);
		return retorno;

	}

	public LinkedList<OrdenOnline> recuperaOrdenesOnline(){
		return ordenOnlineRepository.findAll();
	}
	
	public List<OrdenProducto> recuperaListaOrdenProducto(long idOrdenProducto){
		return ordenOnlineRepository.findByidOrdenOnline(idOrdenProducto).recuperaListaOrdenProducto();
	}
	
	public OrdenOnline recuperaOrdenOnlinePorOrdenProducto(OrdenProducto ordenProducto){
		List<OrdenOnline> ordenesOnline = recuperaOrdenesOnline();
		OrdenOnline resultado = null;
		for (OrdenOnline ordenOnline:ordenesOnline) {
			List<OrdenProducto> ordenesProducto = recuperaListaOrdenProducto(ordenOnline.getIdOrdenOnline());
			for (OrdenProducto orden:ordenesProducto) {
				if (orden.equals(ordenProducto)) resultado = ordenOnline;
			}
		}
		return resultado;
	}
}

package mx.uam.ayd.proyecto.negocio;

import java.util.LinkedList;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.datos.OrdenRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;


@SuppressWarnings("unused")
@Service
public class ServicioOrden {
	
	@Autowired 
	private ProductoRepository productoRepository;

	@Autowired 
	private OrdenRepository ordenRepository;
	
	@Autowired 
	private ServicioProducto servicioProducto;
	 
	@Autowired 
	private ServicioEmpleado servicioEmpleado;

	public List<Orden> ordenesListas = new LinkedList<Orden>();
	public List<Orden> ordenes = new LinkedList<Orden>();
	
	
	public LinkedList<Orden> creaOrdenes(List<String> pedidos, Timestamp hrApertura, String idEmpleado) {//refactorizado
		
		double costoUnitario=-1;
		LinkedList<Orden> ordenesCreadas = new LinkedList<Orden>();
		for(String nombreProducto:pedidos){
			Producto p = servicioProducto.buscaExistenciasDeProducto(nombreProducto).get(0);
			costoUnitario = p.getPrecio();
			Orden aux = new Orden( nombreProducto, hrApertura, costoUnitario, 0);
			ordenRepository.save(aux);
			ordenesCreadas.add(aux);
		}
		
		return ordenesCreadas;
	}
	
	public boolean actualizaOrdenes() {
		this.ordenes = ordenRepository.findAll();
	   	 return true;
	}

	public LinkedList<Orden> creaOrdenes(List<String> pedidos,int numMesa, Timestamp hrApertura) {//refactorizado
		
		double costoUnitario=-1;
		LinkedList<Orden> ordenesCreadas = new LinkedList<Orden>();
		for(String nombreProducto:pedidos){
			Producto p = servicioProducto.buscaExistenciasDeProducto(nombreProducto).get(0);
			costoUnitario = p.getPrecio();
			Orden aux = new Orden( nombreProducto, hrApertura, costoUnitario, 0);
			ordenRepository.save(aux);
			ordenesCreadas.add(aux);
		}
		return ordenesCreadas;
	}
	
	/* Función que recibe un numero de mesa y regresa una lista con todas las ordenes 
	 * que correspondan a ese número de mesa y cuya hora de apertura sea la más reciente
	 */
	
	
	/*Funcion que recibe una lista de ordenes, extrae todos los pedidos y 
	 * los devuelve en una lista de String
	 */
	public LinkedList<String> getTodosLosPedidos(List<Orden> o){
		LinkedList<String> pedidos = new LinkedList<String>();
		for(Orden orden: o) {
			pedidos.add(orden.getProducto());
		}
		return pedidos;
	}
	
	/*Funcion que recibe una lista de ordenes, extrae todos los costos y 
	 * los devuelve en un array de double
	 */
	public double[] getTodosLosCostos(List<Orden> o){
		double[] costos = new double[o.size()];
		for(int i=0; i<o.size();i++) {
			costos[i] = o.get(i).getCostoUnitario();
		}
		return costos;
	}


	public LinkedList<Orden> recuperarOrdenesPorEstado(int estado) {
		return ordenRepository.findByEstado(estado);
	}

	public boolean actualizarEstadoOrden(Orden orden, int estado) {
		Orden aux = ordenRepository.findByidOrden(orden.getIdOrden());
		if(aux.getIdOrden()>=0) {
			aux.setEstado(estado);
			ordenRepository.save(aux);
			return true;
		}	
		return false;
	}
	public Orden eliminaOrden(Orden orden){
		Orden ordenEliminada = orden;
		ordenRepository.delete(orden);
		return ordenEliminada;
	}

	/*Recibe una lista de Timestamp (horas de apertura) y regresa la hora de apertura
	 * mas reciente de la lista
	 */
	@SuppressWarnings("deprecation")
	public Timestamp fechaMasReciente(List<Timestamp> t) {
		java.util.Date utilDate = new java.util.Date();
		long l = utilDate.getTime();
		Timestamp masReciente = new Timestamp(l);
		masReciente.setYear(1);
		for(Timestamp fecha: t) {
			if(fecha.after(masReciente)) {
				masReciente = fecha;
			}
		}
		return masReciente;
	}


	
	
}

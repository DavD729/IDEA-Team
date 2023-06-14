package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RelacionVentaProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialVenta;
import mx.uam.ayd.proyecto.negocio.modelo.RelacionProductoValor;

/**
 * Clase con todas las funcionalidades respectivas al manejo de la relacion producto y venta
 * 
 * @author David
 */

@Service
public class ServicioRelacionProductoValor {
	
	@Autowired
	RelacionVentaProductoRepository repositorioRelacion;
	
	/**
	 * Busca todas las ventas asociadas al Historial de Venta proveeido
	 * 
	 * @param ventaOrigen Historial de Venta Origen
	 * @return Lista de ventas que fueron realizadas en esa fecha
	 */
	
	public List<RelacionProductoValor> recuperaVentas(HistorialVenta ventaOrigen) {
		List<RelacionProductoValor> ventas = new ArrayList<>();
		ventas = repositorioRelacion.findByRelacion(ventaOrigen.getIdHistorial());
		return ventas;
	}
}

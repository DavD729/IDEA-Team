package mx.uam.ayd.proyecto.negocio;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.HistorialVentaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.RelacionVentaProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.RelacionProductoValor;

/**
 * Clase con todas las funcionalidades respectivas al manejo de los historiales de venta
 * 
 * @author David
 *
 */

@Service
public class ServicioHistorialVenta {
	
	@Autowired
	ProductoRepository repositorioProducto;
	
	@Autowired
	HistorialVentaRepository repositorioHistorial;
	
	@Autowired
	RelacionVentaProductoRepository repositorioProductoVenta;
	
	/**
	 * Recupera el historial de venta para una fecha específica.
	 * 
	 * @param fecha La fecha para la cual se desea recuperar o crear el historial de venta.
	 * @return Un objeto HistorialVenta para la fecha especificada.
	 */
	
	public HistorialVenta recuperaHistorial(YearMonth fecha) {
		return repositorioHistorial.findByFecha(fecha);
	}
	
	/**
	 * Crea el historial de ventas para la fecha de venta solicitada.
	 * 
	 * @param fecha Fecha del historial que abarcara las ventas del Mes/Año.
	 * @return El Historial de Ventas para la fecha solicitada
	 */
	
	public HistorialVenta creaHistorial(YearMonth fecha) {
		HistorialVenta historialVenta = recuperaHistorial(fecha);
		if(historialVenta != null) throw new IllegalArgumentException("El historial ya existe en el Repositorio");
		historialVenta = new HistorialVenta();
		historialVenta.setFecha(fecha);
		
		repositorioHistorial.save(historialVenta);
		
		return historialVenta;
	}
	
	/**
	 * Solicita al repositorio un Historial de venta existente para una fecha especifica con
	 * el fin de agregar una venta de ese Mes.
	 * 
	 * @param fecha Fecha del historial a Buscar
	 * @param producto Producto del cual se realizó una venta
	 * @param vendidos Cantidad de los productos vendidos
	 */
	
	public void agregaVentaDeHistorial(YearMonth fecha, Producto producto, int vendidos) {
		HistorialVenta historial = recuperaHistorial(fecha);
		if(historial == null) throw new IllegalArgumentException("El historial solicitado no existe");
		
		for (RelacionProductoValor venta : repositorioProductoVenta.findByRelacion(historial.getIdHistorial())) {
			if (repositorioProducto.findById(venta.getProducto()).get() == producto) {
				venta.setCantidadVendida(venta.getCantidadVendida() + vendidos);
				repositorioProductoVenta.save(venta);
				return;
			}
		}
		
		RelacionProductoValor venta = new RelacionProductoValor();
		venta.setProducto(producto.getIdProducto());
		venta.setCantidadVendida(vendidos);
		venta.setRelacion(historial.getIdHistorial());
		repositorioProductoVenta.save(venta);
	}
	
	/**
	 * Recupera una tabla de datos para una fecha específica del historial de ventas.
	 * 
	 * @param fecha La fecha para la cual se desea obtener la tabla de datos.
	 * @return Una matriz bidimensional de tipo String que representa la tabla de datos.
	 */
	
	public String[][] recuperaTablaDeDatosPorFecha(YearMonth fecha) {
		HistorialVenta historial = recuperaHistorial(fecha);
		if(historial == null) historial = creaHistorial(fecha);
		
		List<RelacionProductoValor> datos = repositorioProductoVenta.findByRelacion(historial.getIdHistorial());
		String[][] matrizDeDatos = new String[datos.size()][3];
		
		for (int index = 0; index < datos.size() && index < 20; index++) {
			Producto producto = repositorioProducto.findById(datos.get(index).getProducto()).get();
			matrizDeDatos[index][0] = producto.getNombre();
			matrizDeDatos[index][1] = String.valueOf(datos.get(index).getCantidadVendida());
			matrizDeDatos[index][2] = String.valueOf(producto.getIdProducto());
		}
		
		return matrizDeDatos;
	}
}

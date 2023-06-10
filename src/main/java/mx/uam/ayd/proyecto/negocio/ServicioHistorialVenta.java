package mx.uam.ayd.proyecto.negocio;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.HistorialVentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

/**
 * Clase con todas las funcionalidades respectivas al manejo de los historiales de venta
 * 
 * @author David
 *
 */

@Service
public class ServicioHistorialVenta {
	
	@Autowired
	HistorialVentaRepository repositorioHistorial;
	
	/**
	 * Recupera el historial de ventas.
	 * 
	 * @return Una lista de objetos HistorialVenta que representa el historial de ventas.
	 */
	
	public List<HistorialVenta> recuperaHistorialVentas() {
		List<HistorialVenta> historiales = new ArrayList<>();
		repositorioHistorial.findAll().forEach(historial -> {
			historiales.add(historial);
		});
		return historiales;
	}
	
	/**
	 * Recupera o crea un historial de venta para una fecha específica.
	 * 
	 * @param fecha La fecha para la cual se desea recuperar o crear el historial de venta.
	 * @return Un objeto HistorialVenta para la fecha especificada.
	 */
	
	public HistorialVenta recuperaOrCreaHistorial(YearMonth fecha) {
		HistorialVenta historial = repositorioHistorial.findByFecha(fecha);
		if(historial == null) {
			historial = new HistorialVenta();
			historial.setFecha(fecha);
		}
		return historial;
	}
	
	/**
	 * Agrega una venta al historial de ventas existente o crea un nuevo historial para la fecha de la venta.
	 * 
	 * @param venta La venta que se va a agregar al historial.
	 * @param relacionVenta Un mapa que contiene la relación de productos vendidos y la cantidad vendida.
	 */
	
	public void agregaVentaDeHistorial(Venta venta, Map<Producto, Integer> relacionVenta) {
		YearMonth fecha = YearMonth.of(venta.getDate().getYear(), venta.getDate().getMonth());
		HistorialVenta historial = recuperaOrCreaHistorial(fecha);
		relacionVenta.forEach((producto, cantidadVendidos) -> {
			int value = historial.getProductosVendidos().getOrDefault(producto, -1);
			if(value != -1) {
				value += cantidadVendidos;
			} else {
				historial.getProductosVendidos().put(producto, cantidadVendidos);
			}
		});
	}
	
	/**
	 * Recupera una tabla de datos para una fecha específica del historial de ventas.
	 * 
	 * @param fecha La fecha para la cual se desea obtener la tabla de datos.
	 * @return Una matriz bidimensional de tipo String que representa la tabla de datos.
	 */
	
	public String[][] recuperaTablaDeDatosPorFecha(YearMonth fecha) {
		HistorialVenta historial = recuperaOrCreaHistorial(fecha);
		Map<Producto, Integer> datos = historial.getProductosVendidos();
		String[][] matrizDeDatos = new String[datos.size()][3];
		Set<Producto> productos = datos.keySet();
		Collection<Integer> vendidos = datos.values();
		for(int index = 0; index < datos.size() && index < 20; index++) {
			matrizDeDatos[index][0] = ((Producto)productos.toArray()[index]).getNombre();
			matrizDeDatos[index][1] = vendidos.toArray()[index].toString();
			matrizDeDatos[index][2] = String.valueOf(((Producto)productos.toArray()[index]).getIdProducto());
		}
		return matrizDeDatos;
	}
}

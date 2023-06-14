package mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioHistorialVenta;

/**
 * Esta clase contiene todos los metodos para manipular los servicios y
 * la visualización de los datos referentes al historial de ventas.
 * 
 * @author David
 */

@Component
public class ControlHistorialVenta {
	
	@Autowired
	private VentanaHistorialVenta ventanaHistorialVenta;
	
	@Autowired
	private ServicioHistorialVenta servicioHistorial;
	
	/**
	 * Prepara la ventana del historial de venta y la muestra
	 */
	
	public void inicio() {
		ventanaHistorialVenta.muestraVentana(this);
	}
	
	/**
	 * Finaliza la ventana del historial de venta
	 */
	public void finalizaControlHistorial() {
		ventanaHistorialVenta.dispose();
	}
	
	/**
	 * Solicita al servicio de historiales de venta los datos referentes a una fecha especifica en forma de tabla
	 * 
	 * @param fecha Fecha del historial a Buscar
	 * @return Historial de venta de la fecha solicitada
	 */
	public String[][] recuperaTabla(YearMonth fecha) {
		return servicioHistorial.recuperaTablaDeDatosPorFecha(fecha);
	}
}

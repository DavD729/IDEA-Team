package mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioHistorialVenta;

@Component
public class ControlHistorialVenta {
	
	@Autowired
	private VentanaHistorialVenta ventanaHistorialVenta;
	
	@Autowired
	private ServicioHistorialVenta servicioHistorial;
	
	public void inicio() {
		ventanaHistorialVenta.muestraVentana(this);
	}
	
	public void exportaTablaPDF() {
		
	}
	
	public void finalizaControlHistorial() {
		ventanaHistorialVenta.dispose();
	}
	
	public Object[][] recuperaTabla(YearMonth fecha) {
		return servicioHistorial.recuperaTablaDeDatosPorFecha(fecha);
	}
}

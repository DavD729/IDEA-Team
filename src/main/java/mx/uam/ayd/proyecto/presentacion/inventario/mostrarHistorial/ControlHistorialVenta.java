package mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial;

import org.springframework.beans.factory.annotation.Autowired;

public class ControlHistorialVenta {
	
	@Autowired
	private VentanaHistorialVenta ventanaHistorialVenta;
	
	public void inicio() {
		ventanaHistorialVenta.muestraVentana(this);
	}
}

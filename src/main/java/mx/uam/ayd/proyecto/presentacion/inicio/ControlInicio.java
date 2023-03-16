package mx.uam.ayd.proyecto.presentacion.inicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.inventario.ControlInventario;

@Component
public class ControlInicio {
	
	@Autowired
	private ControlInventario controlInv;
	
	@Autowired
	private VentanaInicio ventanaInicio;
	
	public void inicio() {
		ventanaInicio.muestraVentana(this);
	}
	
	public void muestraContenidoInventario() {
		controlInv.inicia();
	}
	
	/**
	 * Este apartado es para revisar si multiples ventanas estan abiertas, para cerrar al cambiar
	 * WIP
	 */
	public void cambiaVentana() {
		controlInv.checaVentanas();
	}
}

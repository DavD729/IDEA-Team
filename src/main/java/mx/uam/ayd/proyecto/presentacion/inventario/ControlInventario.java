package mx.uam.ayd.proyecto.presentacion.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.agregarCategoria.ControlAgregarCategoria;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.ControlAgregarProducto;

@Component
public class ControlInventario {
	
	@Autowired
	private ControlAgregarCategoria controlCategoria;
	
	@Autowired
	private ControlAgregarProducto controlProducto;
	
	@Autowired
	private VentanaInventario ventanaI;
	
	public void inicia() {
		ventanaI.muestraContenido(this);
	}
	
	public void agregaCategoria() {
		controlCategoria.inicio();	
	}
	
	public void agregaProducto() {
		controlProducto.inicio();
	}
	
	public void finalizaControlInventario() {
		ventanaI.setVisible(false);
	}

	public void checaVentanas() {
		controlProducto.checaVentana();
		if(ventanaI.isVisible()) ventanaI.setVisible(false);
	}
}

package mx.uam.ayd.proyecto.presentacion.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarCategoria.ControlAgregarCategoria;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarProducto.ControlAgregarProducto;

@Component
public class ControlInventario {
	
	@Autowired
	private ControlAgregarCategoria controlCategoria;
	
	@Autowired
	private ControlAgregarProducto controlProducto;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
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
	
	public Object[][] recuperaTablaProductos() {
		return servicioProducto.recuperaTablaDeProductos();
	}
	
	public void finalizaControlInventario() {
		ventanaI.dispose();
	}
}

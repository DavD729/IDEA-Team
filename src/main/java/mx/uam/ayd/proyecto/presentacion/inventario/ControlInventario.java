package mx.uam.ayd.proyecto.presentacion.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarCategoria.ControlAgregarCategoria;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarProducto.ControlAgregarProducto;

@Slf4j
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
	
	public void listaProductos() {
		servicioProducto.recuperaProductos().forEach(producto -> {
			log.info(String.format("Producto: %s, con ID: %d", producto.getNombre(), producto.getIdProducto()));
		});
	}
	
	public void finalizaControlInventario() {
		ventanaI.dispose();
	}
}

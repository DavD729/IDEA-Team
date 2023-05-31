package mx.uam.ayd.proyecto.presentacion.inventario.agregarProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCategoria;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.inventario.VentanaInventario;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarCategoria.ControlAgregarCategoria;

@Component
public class ControlAgregarProducto {
	
	@Autowired
	private VentanaInventario ventanaInventario;
	
	@Autowired
	private ControlAgregarCategoria controlCategoria;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioCategoria servicioCategoria;
	
	@Autowired
	private VentanaAgregarProducto ventanaProducto;
	
	public void inicio() {
		List<Categoria> categorias = servicioCategoria.recuperaCategorias();
		ventanaProducto.muestraVentana(this, controlCategoria, categorias);
	}
	
	public Producto registraProducto(String nombre, String categoria, int cantidad, double precio, String descripcion) {
		Producto registro = null;
		try {
			registro = servicioProducto.agregaProducto(nombre, categoria, cantidad, precio, descripcion);
		} catch(IllegalArgumentException e) {
			ventanaProducto.muestraErrorConMensaje("El producto no pudo ser registrado: " + e.getMessage());
		}
		
		this.finaliza();
		return registro;
	}
	
	public void finaliza() {
		ventanaProducto.dispose();
		ventanaInventario.updateTable();
	}
}

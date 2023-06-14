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

/**
 * Esta Clase contiene los metodos para manipular los servicios referentes a la
 * sección para registrar productos al Inventario
 * 
 * @author David
 */

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
	
	/**
	 * Prepara e inicia la Ventana para agregar productos solicitando las categorias registradas
	 */
	
	public void inicio() {
		List<Categoria> categorias = servicioCategoria.recuperaCategorias();
		ventanaProducto.muestraVentana(this, controlCategoria, categorias);
	}
	
	/**
	 * Solicita al servicio que manipula los productos el registro de un producto nuevo 
	 * 
	 * @param nombre Nombre del producto a registrar
	 * @param categoria Categoria asignada del producto
	 * @param cantidad Cantidad de productos que habrán en almacen
	 * @param precio Precio de los productos por el que serán vendidos
	 * @param descripcion Descripción utilitaria que identificara las caracteristicas del producto
	 * @return Producto registrado en la base de datos
	 */
	
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
	
	/**
	 * Solicita al servicio de productos si hay productos registrados con ese nombre
	 * 
	 * @param nombre Nombre del producto(s) a buscar
	 * @return Booleano que indica si se encontro o no productos
	 */
	
	public boolean hayExistenciasDeProductos(String nombre) {
		return !servicioProducto.buscaExistenciasDeProducto(nombre).isEmpty();
	}
	
	/**
	 * Finaliza la ventana del registro del producto.
	 * Actualiza la tabla de la ventana de inventario
	 */
	
	public void finaliza() {
		ventanaProducto.dispose();
		ventanaInventario.updateTable();
	}
}

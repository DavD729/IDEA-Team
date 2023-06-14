package mx.uam.ayd.proyecto.presentacion.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarCategoria.ControlAgregarCategoria;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarProducto.ControlAgregarProducto;
import mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial.ControlHistorialVenta;

/**
 * Esta clase contiene los metodos para manipular y acceder a los diferentes controles
 * que abarcan la sección de inventario como "ControlCategoria", "ControlProducto" y "ControlHistorial"
 * 
 * @author David
 */
@Component
public class ControlInventario {
	
	@Autowired
	private ControlAgregarCategoria controlCategoria;
	
	@Autowired
	private ControlAgregarProducto controlProducto;
	
	@Autowired
	private ControlHistorialVenta controlHistorial;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private VentanaInventario ventanaInventario;
	
	/**
	 * Prepara y muestra la ventana de Inventario
	 */
	
	public void inicia() {
		controlCategoria.inicio();
		ventanaInventario.muestraContenido(this);
	}
	
	/**
	 * Llama y prepara el control de Producto para argegar Productos
	 */
	
	public void iniciaControlProducto() {
		controlProducto.inicio();
	}
	
	/**
	 * Llama y prepara el control de historial de venta 
	 */
	
	public void muestraHistorialVenta() {
		controlHistorial.inicio();
	}
	
	/**
	 * Solicita al servicio de productos la informacion completa de los productos en forma de tabla
	 * 
	 * @return Tabla de todos los productos registrados
	 */
	public Object[][] recuperaTablaProductos() {
		return servicioProducto.recuperaTablaDeProductos();
	}
	
	/**
	 * Finaliza y cierra la ventana referente al Inventario
	 */
	public void finalizaControlInventario() {
		ventanaInventario.dispose();
	}
}

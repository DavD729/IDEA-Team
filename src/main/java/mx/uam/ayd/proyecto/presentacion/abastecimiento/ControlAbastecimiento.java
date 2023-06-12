package mx.uam.ayd.proyecto.presentacion.abastecimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Component
public class ControlAbastecimiento {
	
	@Autowired
	private VentanaAbastecimiento ventanaModifica;
	
	@Autowired 
	private ServicioProducto servicioProducto;
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {
		ventanaModifica.muestra(this);
	}
	
	/*
	 * REcupera la cantidad de productos en existencia
	 */
	public int recuperaCantidadActual() {
		return servicioProducto.getNumeroProductos();
	}
	
	/*
	 * Recupera los productos de la base de datos
	 */
	public Object[][] obtenMatriz() {
		return servicioProducto.recuperaMatriz();
	}
	
	/*
	 * Le envia la lista con las cantidades y precios actualizados
	 */
	public void actualizaProductos(List<Producto> productos) {
		servicioProducto.acualiza(productos);
	}
	
	/*
	 * Finaliza la ventana de abastecimiento
	 */
	public void finalizaControlAbastecimiento() {
		ventanaModifica.dispose();
	}
}

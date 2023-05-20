package mx.uam.ayd.proyecto.presentacion.abastecimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Component
public class Control_vista {
	
	@Autowired
	private Vista_modifica ventanaModifica;
	
	@Autowired 
	private ServicioProducto servicioProducto;
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {
		ventanaModifica.muestra(this);
	}
	
	public int recuperaCantidadActual() {
		return servicioProducto.getNumeroProductos();
	}
	
	public Object[][] obtenMatriz() {
		return servicioProducto.recuperaMatriz();
	}
	
	public void actualizaProductos(List<Producto> productos) {
		servicioProducto.acualiza(productos);
	}
	
	public void finalizaControlAbastecimiento() {
		ventanaModifica.dispose();
	}
}


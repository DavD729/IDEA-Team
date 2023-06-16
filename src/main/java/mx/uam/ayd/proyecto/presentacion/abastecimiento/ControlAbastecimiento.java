package mx.uam.ayd.proyecto.presentacion.abastecimiento;

import java.util.List;
import java.util.Optional;

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
	 * Recupera los productos de la base de datos
	 */
	public Object[][] obtenMatriz() {
		return servicioProducto.recuperaTablaDeProductos();
	}
	
	public Optional<Producto> recuperaProducto(long id) {
		return servicioProducto.buscaProducto(id);
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

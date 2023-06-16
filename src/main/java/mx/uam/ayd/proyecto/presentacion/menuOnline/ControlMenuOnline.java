package mx.uam.ayd.proyecto.presentacion.menuOnline;

import java.sql.Timestamp;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioOrdenOnline;
import mx.uam.ayd.proyecto.negocio.ServicioOrdenProducto;
import mx.uam.ayd.proyecto.negocio.ServicioProducto1;
import mx.uam.ayd.proyecto.negocio.modelo.OrdenOnline;
import mx.uam.ayd.proyecto.negocio.modelo.Producto1;


@SuppressWarnings("unused")
@Component
public class ControlMenuOnline {
	

	@Autowired
	private VentanaMenuOnline ventanaMenuOnline;
	
	@Autowired
	private VentanaCarrito ventanaCarrito;
	
	
	
	@Autowired
	private ServicioProducto1 servicioProducto;
	
		
	@Autowired
	private ServicioOrdenProducto servicioOrdenProducto;
	
	/*
	 * Muestra la VentanaMenuOnline de este control
	 * @param idEmpleado El id del empleado que accede a su perfil
	 */
	
	public void inicia() {
		ventanaMenuOnline.muestra(this, servicioProducto.getMenuOnline());
	}



	/**
	 * Muestra la ventanaCarrito de este control 
	 * @param productosElegidos Los productoss que ha elegido el Rmpleado para su compra online
	 */
	public void goToCarrito(LinkedList<Producto1> productoElegidos) {
		ventanaCarrito.muestra(this, productoElegidos);
	}

	/**
	 * Hace visible la ventanaMenuOnline de este control
	 */
	public void ventanaMenuEnabled() {
		ventanaMenuOnline.setVisible(true);;
		
	}

	
	
	/**
	 * Cuenta el numero de incidencias de un Platillo p en una LinkedList<Producto>
	 * @param p El producto cuyo numero de incidencias se requiere contar
	 * @param l La lista de producto sobre la que se cuentan las incidencias de un Producto p
	 * @return El número de incidencias de un Producto p en una lista de producto
	 */
	public int cuentaIncidencias(Producto1 p, LinkedList<Producto1> l) {
		int incidencias=0;
		for(Producto1 producto: l) {
			if(producto.equals(p)) {
				incidencias++;
			}
		}
		return incidencias;
	}


	public boolean creaOrden(LinkedList<Producto1> pedidos, float total) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}


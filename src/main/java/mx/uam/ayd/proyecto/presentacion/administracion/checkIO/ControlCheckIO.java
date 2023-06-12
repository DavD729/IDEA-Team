package mx.uam.ayd.proyecto.presentacion.administracion.checkIO;

import java.util.List;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioPuesto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.presentacion.administracion.actualizarEmpleado.VentanaActualizarEmpleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlCheckIO {

	@Autowired
	private VentanaCheckIO ventanaCheckIO;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	public void inicia() {
		
		ventanaCheckIO.muestra(this);
	}
}

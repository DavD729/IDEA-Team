package mx.uam.ayd.proyecto.presentacion.administracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import mx.uam.ayd.proyecto.presentacion.administracion.actualizarEmpleado.ControlActualizarEmpleado;

import mx.uam.ayd.proyecto.presentacion.administracion.agregarEmpleados.ControlAddEmpleado;
/**
 * Esta clase lleva el flujo de control de la ventana principal
 * 
 * @author humbertocervantes
 *
 */
@Component
public class ControlAdministración {

	@Autowired
	private ControlAddEmpleado controlAddEmpleado;
	
	@Autowired
	private ControlActualizarEmpleado controlActualizarEmpleado;
	
	@Autowired
	private VentanaAdministracion ventana;
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {
		ventana.muestra(this);
	}


	//Inicia el flujo de la venatan Agregar Empleado
	public void AddEmpleado() {
		controlAddEmpleado.inicia();
	}
	
	//Inicia el flujo de la venatan Actualizar Empleado
	public void ActualizarEmpleado() {
		controlActualizarEmpleado.inicia();
	}

}

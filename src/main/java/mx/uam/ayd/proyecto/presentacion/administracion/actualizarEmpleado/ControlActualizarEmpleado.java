package mx.uam.ayd.proyecto.presentacion.administracion.actualizarEmpleado;

import java.util.List;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioPuesto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ControlActualizarEmpleado {
	@Autowired
	private VentanaActualizarEmpleado ventanaActualizarEmpleado;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioPuesto servicioPuesto;
	
	public void inicia() {
		
		ventanaActualizarEmpleado.muestra(this);
	}
	
	// Control para Recuperar el empleado sobre el que se trabajara
	public Empleado recuperarEmpleado(String email) {
		 List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		 Empleado empleado=servicioEmpleado.recuperarEmpleado(email);	
		 ventanaActualizarEmpleado.muestraEmpleadoRecuperado(empleado,puestos);
		 return empleado;
	}
	
	// Control para actualizar datos del Empleado
	public void actualizarEmpleado(Empleado empleado,Puesto puesto,String nombre,
			String ApellidoP, 
			String ApellidoM,
			String Direccion,
			String Tel,
			String Email,
			String Tarea,
			String Puestos) {
		try {
			servicioEmpleado.actualizarEmpleado(empleado,puesto,nombre,ApellidoP,ApellidoM,Direccion,Tel,Email,Tarea,Puestos);
			ventanaActualizarEmpleado.muestraDialogoConMensaje("Empleado Actualizado con Exito");
		}catch (Exception ex) {
			ventanaActualizarEmpleado.muestraDialogoConMensaje("Error al Actualizar empleado "+ex.getMessage());
		}
		termina();		
	}
	
	//Control para recuperar el grupo el Puesto al que pertenece el empleado
	public Puesto recuperaPuestoEmpleado(Empleado empleado) {
		List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		Puesto puesto=servicioEmpleado.recuperaPuestoEmpleado(empleado, puestos);
		return puesto;
	}
	
	//Finaliza la funcion  Actualizar Empleado
	public void termina() {
		ventanaActualizarEmpleado.setVisible(false);
	}

}

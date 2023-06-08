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
	
	public Empleado recuperarEmpleado(String email) {
		 List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		 Empleado empleado=servicioEmpleado.recuperarEmpleado(email);	
		 ventanaActualizarEmpleado.muestraEmpleadoRecuperado(empleado,puestos);
		 return empleado;
	}
	
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
			ventanaActualizarEmpleado.muestraDialogoConMensaje("Empleado Actualizado");
		}catch (Exception ex) {
			ventanaActualizarEmpleado.muestraDialogoConMensaje("Error al Actualizar empleado "+ex.getMessage());
		}
		termina();		
	}
	
	public Puesto recuperaNomPuestoEmpleado(Empleado empleado) {
		List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		Puesto puesto=servicioEmpleado.recuperaPuestoEmpleado(empleado, puestos);
		return puesto;
	}
	
	public void termina() {
		ventanaActualizarEmpleado.setVisible(false);
	}

}

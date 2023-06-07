package mx.uam.ayd.proyecto.presentacion.actualizarEmpleado;

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
	private VentanaActualizarEmpleado ventanaAEmpleado;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioPuesto servicioPuesto;
	
	public void inicia() {
		
		ventanaAEmpleado.muestra(this);
	}
	
	public Empleado recuperarEmpleado(String email) {
		List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		 Empleado empleado=servicioEmpleado.recuperarEmpleado(email);	
		 ventanaAEmpleado.muestraEmpleadoRecuperado(empleado,puestos);
		 return empleado;
	}
	/*
	public String recuperaNomPuestoEmpleado(Empleado empleado) {
		//System.out.println("EMpleado esde recuperar puesto"+empleado.getNombre());
		List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		 Puesto puesto=servicioEmpleado.recuperaPuestoE(empleado, puestos);
		 System.out.println("Nombre puesto recuperado"+puesto.getNombre());
		return puesto.getNombre();
	}
	*/
	public void actualizarEmpleado(Empleado empleado,String Nombre,
			String ApellidoP, 
			String ApellidoM,
			String Direccion,
			String Tel,
			String Email,
			String Tarea,
			String Puestos) {
		try {
			System.out.println("EMPLEADO NOMBRE EN EL control: "+empleado.getNombre());
			servicioEmpleado.actualizarEmpleado(empleado,Nombre,ApellidoP,ApellidoM,Direccion,Tel,Email,Tarea,Puestos);
			ventanaAEmpleado.muestraDialogoConMensaje("Empleado Actualizado");
		}catch (Exception ex) {
			ventanaAEmpleado.muestraDialogoConMensaje("Error al Actualizar empleado "+ex.getMessage());
		}
		termina();		
	}
	
	public void termina() {
		ventanaAEmpleado.setVisible(false);
	}
	
}

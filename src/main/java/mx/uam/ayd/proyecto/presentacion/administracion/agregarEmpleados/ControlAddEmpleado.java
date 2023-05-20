package mx.uam.ayd.proyecto.presentacion.administracion.agregarEmpleados;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.*;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;



@Component
public class ControlAddEmpleado {
	
	@Autowired
	private VentanaAddEmpleado ventanaAddEmpleado;
	
	@Autowired
	private ServicioPuesto servicioPuesto;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	//Inicia el flujo para agregar un empleado de acuerdo al tipo de puesto que le corresponde
	public void inicia() {
		List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		ventanaAddEmpleado.muestra(this, puestos);
	}
	
	public void addEmpleado(String Nombre,
							String ApellidoP, 
							String ApellidoM,
							String Direccion,
							String Tel,
							String Email,
							String Tarea,
							String Puesto) {
		
		try {
			servicioEmpleado.agregarEmpleado(Nombre,ApellidoP,ApellidoM,Direccion,Tel,Email,Tarea, Puesto);
			ventanaAddEmpleado.muestraDialogoConMensaje("Usuario agregado con exito");
		}catch (Exception ex) {
			ventanaAddEmpleado.muestraDialogoConMensaje("Error al agregar empleado "+ex.getMessage());
		}
		
		termina();
	}
	
	public void termina() {
		ventanaAddEmpleado.setVisible(false);		
	}

}

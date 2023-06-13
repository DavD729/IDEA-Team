package mx.uam.ayd.proyecto.presentacion.administracion.checkIO;

import java.util.List;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioPuesto;
import mx.uam.ayd.proyecto.negocio.ServicioRegistro;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.negocio.modelo.Registro;
import mx.uam.ayd.proyecto.presentacion.administracion.actualizarEmpleado.VentanaActualizarEmpleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlCheckIO {

	@Autowired
	private VentanaCheckIO ventanaCheckIO;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioPuesto servicioPuesto;
	
	@Autowired ServicioRegistro servicioRegistro;
	
	public void inicia() {
		List <Registro> registros=servicioRegistro.recuperarRegistros();
		ventanaCheckIO.muestra(this, registros);
	}
	
	// Control para Recuperar el empleado sobre el que se trabajara
	public Empleado recuperarEmpleado(String email) {
		// List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		 Empleado empleado=servicioEmpleado.recuperarEmpleado(email);	
		 ventanaCheckIO.muestraEmpleadoRecuperado(empleado);
		 return empleado;
	}
	
	//Control para recuperar el grupo el Puesto al que pertenece el empleado
	public Puesto recuperaPuestoEmpleado(Empleado empleado) {
		List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		Puesto puesto=servicioEmpleado.recuperaPuestoEmpleado(empleado, puestos);
		return puesto;
	}
	
	public void addRegistro(Empleado empleado, String Registro) {
		try {
			servicioEmpleado.agregarEmpleadoRegistro(empleado,Registro);
			ventanaCheckIO.muestraDialogoConMensaje("Empleado checado con exito");
		}catch (Exception ex) {
			ventanaCheckIO.muestraDialogoConMensaje("Error al checar empleado "+ex.getMessage());
		}
		
		termina();
	}
	
	public void termina() {
		ventanaCheckIO.setVisible(false);		
	}
}

package mx.uam.ayd.proyecto.presentacion.administracion.checkIO;

import java.util.List;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioPuesto;
import mx.uam.ayd.proyecto.negocio.ServicioRegistro;
import mx.uam.ayd.proyecto.negocio.ServicioTiempo;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.negocio.modelo.Registro;
import mx.uam.ayd.proyecto.presentacion.administracion.actualizarEmpleado.VentanaActualizarEmpleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlCheckIO {
	/*Instancias*/
	@Autowired
	private VentanaCheckIO ventanaCheckIO;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ServicioPuesto servicioPuesto;
	
	@Autowired 
	private ServicioRegistro servicioRegistro;
	
	@Autowired
	private ServicioTiempo servicioTiempo;
	
	public void inicia() {
		ventanaCheckIO.muestra(this);
	}
	
	/* Control para Recuperar el empleado sobre el que se trabajara*/
	public Empleado recuperarEmpleado(String email) {
		 Empleado empleado=servicioEmpleado.recuperarEmpleado(email);
		 List <Registro> registros=servicioRegistro.recuperarRegistros();
		 ventanaCheckIO.muestraEmpleadoRecuperado(empleado,registros);
		 return empleado;
	}
	
	/*Control para recuperar el grupo el Puesto al que pertenece el empleado*/
	public Puesto recuperaPuestoEmpleado(Empleado empleado) {
		List <Puesto> puestos= servicioPuesto.recuperaPuestos();
		Puesto puesto=servicioEmpleado.recuperaPuestoEmpleado(empleado, puestos);
		return puesto;
	}
	
	/*Control para agregar al registro el chequeo del empleado de entrada y salida*/
	public void addRegistroTiempo(Empleado empleado, String Registro,String anio,String mes, String dia,String hora,String min,String segundos) {
		try {
			servicioTiempo.agregarEmpleadoRegistroTiempo(empleado,Registro,anio,mes,dia,hora,min,segundos);
			ventanaCheckIO.muestraDialogoConMensaje(empleado.getNombre()+" "+empleado.getApellidoP()+" "
					+"Registro a la tienda exitoso el "
					+dia+"/"+mes+"/"+anio+" a las "+hora+":"+min+":"+segundos);
		}catch (Exception ex) {
			ventanaCheckIO.muestraDialogoConMensaje("Error al checar empleado "+ex.getMessage());
		}
		
		termina();
	}
	
	/*Termina el proceso del CheckIO*/	
	public void termina() {
		ventanaCheckIO.setVisible(false);		
	}
}

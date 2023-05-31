package mx.uam.ayd.proyecto.presentacion.actualizarEmpleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlActualizarEmpleado {

	@Autowired
	private VentanaActualizarEmpleado ventanaAEmpleado;
	
	public void inicia() {
		ventanaAEmpleado.muestra(this);
	}
	
	public void recuperarEmpleado(String email) {
		ventanaAEmpleado.muestraDialogoConMensaje("Email ingresado: "+email);
		
	}
	public void termina() {
		ventanaAEmpleado.setVisible(false);
	}
	
}

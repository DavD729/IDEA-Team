package mx.uam.ayd.proyecto.presentacion.inicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.abastecimiento.Control_vista;
import mx.uam.ayd.proyecto.presentacion.administracion.ControlAdministración;
import mx.uam.ayd.proyecto.presentacion.corte.ControlGuardaGanancias;
import mx.uam.ayd.proyecto.presentacion.inventario.ControlInventario;

/**
 * Control del Menú principal del programa, este controla los movimientos y usos
 * hacia otros controles y/o funciones del programa
 * 
 * @author David
 *
 */
@Component
public class ControlInicio {
	
	@Autowired
	private ControlAdministración controlAdministracion;
	
	@Autowired
	private Control_vista controlAbastecimiento;
	
	@Autowired
	private ControlInventario controlInv;
	
	@Autowired
	private ControlGuardaGanancias controlCorte;
	
	@Autowired
	private VentanaInicio ventanaInicio;
	
	public void inicio() {
		ventanaInicio.muestraVentana(this);
	}
	
	public void muestraContenidoAdministracion() {
		controlAdministracion.inicia();
	}
	
	public void muestraContenidoAbastecimiento() {
		controlAbastecimiento.inicia();
	}
	
	public void muestraContenidoInventario() {
		controlInv.inicia();
	}
	
	public void muestraContenidoCorte() {
		controlCorte.inicia();
	}
}

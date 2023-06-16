package mx.uam.ayd.proyecto.presentacion.inicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.abastecimiento.ControlAbastecimiento;
import mx.uam.ayd.proyecto.presentacion.administracion.ControlAdministración;
import mx.uam.ayd.proyecto.presentacion.corte.ControlGuardaGanancias;
import mx.uam.ayd.proyecto.presentacion.inventario.ControlInventario;
import mx.uam.ayd.proyecto.presentacion.menuOnline.ControlMenuOnline;

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
	private ControlAbastecimiento controlAbastecimiento;
	
	@Autowired
	private ControlInventario controlInventario;
	
	@Autowired
	private ControlMenuOnline controlVenta;
	
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
		controlInventario.inicia();
	}
	
	public void muestraContenidoVenta() {
		controlVenta.inicia();
	}
	
	public void muestraContenidoCorte() {
		controlCorte.inicia();
	}
}

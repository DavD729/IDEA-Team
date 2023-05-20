package mx.uam.ayd.proyecto.presentacion.corte;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCorte;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Corte;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Component
public class ControlGuardaGanancias {
	
	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private ServicioCorte servicioCorte;
	
	@Autowired
	private VentanaGuardaGanancias ventana;
	
	/**
	 * Inicia la historia de usuario
	 * 
	 */
	public void inicia() {
		LocalDate Fecha = LocalDate.now();
		List <Venta> ventas = servicioVenta.recuperaVentas(Fecha);
		Corte corte = servicioCorte.sumaVentas(ventas);
		ventana.muestra(this, corte);
	}
	
	public Corte guardarCorte(LocalDate Date, double Efectivo, double Tarjeta, double Credito, double Vales, double Total) {
		Corte corte = null;
		try {
			corte = servicioCorte.guardarCorte(Date, Efectivo,Tarjeta,Credito,Vales,Total);
			ventana.muestraDialogoConMensaje("Corte de caja guardado exitosamente");
		} catch(Exception ex) {
			ventana.muestraDialogoConMensaje("Error al guardar el corte de caja");
		}
		return corte;
	}
	
	public Corte buscaCorte(LocalDate Date) {
		Corte corte = servicioCorte.recuperaCorte(Date);
		return corte;
	}
	
	public void finaliza() {
		ventana.dispose();
	}

}

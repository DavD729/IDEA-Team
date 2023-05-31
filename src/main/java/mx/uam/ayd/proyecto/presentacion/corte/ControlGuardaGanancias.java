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
		Corte corte = new Corte();
		corte.setTipoOperacion(1);
		List <Venta> ventas = servicioVenta.recuperaVentas(Fecha);
		corte = servicioCorte.sumaVentas(ventas,Fecha,corte.getTipoOperacion());
		ventana.muestra(this, corte, corte.getTipoOperacion());
	}
	
	public Corte guardarCorte(LocalDate Date, double Efectivo, double Tarjeta, double Credito, double Vales, double Total, 
			int tipoOperacion) {
		Corte corte = null;
		try {
			corte = servicioCorte.guardarCorte(Date, Efectivo,Tarjeta,Credito,Vales,Total,tipoOperacion);
			ventana.muestraDialogoConMensaje("Corte de caja guardado exitosamente");
		} catch(Exception ex) {
			ventana.muestraDialogoConMensaje("Error al guardar el corte de caja");
		}
		return corte;
	}
	
	public Corte buscaCorte(LocalDate Date, int tipoOperacion) {
		Corte corte = servicioCorte.recuperaCorte(Date, tipoOperacion);
		return corte;
	}
	
	public void finaliza() {
		ventana.dispose();
	}

}

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
	*Inicia la Historia de Usuario, obtiene la fecha actual del sistema y crean una nueva entidad tipo Corte, luego crea una Lista
	*	de ventas para recuperar las ventas del dia, esas ventas las suma segun el tipo de pago y los totales los guarda en el corte
	*	al final solo muestra el corte en la ventana
	**/
	public void inicia() {
		LocalDate fecha = LocalDate.now();
		List <Venta> ventas = servicioVenta.recuperaVentas(fecha);
		Corte corte = new Corte();
		
		corte.setTipoOperacion(1);
		corte = servicioCorte.sumaVentas(ventas,fecha,corte.getTipoOperacion());
		ventana.muestra(this, corte, corte.getTipoOperacion());
	}
	
	/**
	*Guarda el corte del día en la base de Datos
	*
	* @param LocalDate fecha: Fecha actual del sistema.
	* 		double efectivo: El total de ventas/compras que se hicieron con efectivo.
	* 		double tarjeta: El total de ventas/compras que se hicieron con tarjeta.
	* 		double credito: El total de ventas/compras que se hicieron con credito en tienda.
	* 		double vales: El total de ventas/compras que se hicieron con vales de despensa.
	* 		double total: El total de ventas/compras que se hicieron en el dia.
	* 		int tipoOperacion: Tipo de Corte (Ingresos = 1, Egresos = 2)
	* 
	* @return void solo guarda el corte en la base de datos
	**/
	public void guardarCorte(LocalDate date, double efectivo, double tarjeta, double credito, double vales, double total, 
			int tipoOperacion) {
		try {
			if(servicioCorte.guardarCorte(date, efectivo, tarjeta, credito, vales, total, tipoOperacion))
				ventana.muestraDialogoConMensaje("Corte de caja guardado exitosamente");
		} catch(Exception ex) {
			ventana.muestraDialogoConMensaje("Error al guardar el corte de caja");
		}
	}
	
	/**
	*Busca el corte en la base de datos
	*
	* @param LocalDate fecha: Fecha de la cual se quiere recuperar el corte.
	* 		int tipoOperacion: Tipo de Corte (Ingresos = 1, Egresos = 2)
	* 
	* @return Corte: El corte del dia.
	**/
	public Corte buscaCorte(LocalDate date, int tipoOperacion) {
		Corte corte = servicioCorte.recuperaCorte(date, tipoOperacion);
		return corte;
	}
}

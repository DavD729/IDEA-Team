package mx.uam.ayd.proyecto.negocio;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.CorteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Corte;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Slf4j
@Service

public class ServicioCorte {
	@Autowired
	private CorteRepository corteRepository;
	
	/**
	*Recibe y suma todas las ventas del día dependiendo del tipo de Operacion (Efectivo, Tarjeta de Credito, Vales, etc), 
	*	se crea un nuevo Objeto de tipo Corte para guardar los totales de las ventas del día y se le asigna la fecha actual,
	*	así como el tipo de Operacion (si es Corte de Ingresos o Egresos).
	*
	* @param List<Venta> ventas: La lista de ventas del dia.
	* 		LocalDate fecha: La fecha actual del Sistema.
	* 		int tipoOperacion: Tipo de Corte (Ingresos = 1, Egresos = 2).
	* 
	* @return Corte: Un nuevo objeto que representa el corte del dia.
	**/
	public Corte sumaVentas(List<Venta> ventas, LocalDate fecha, int tipoOperacion) {
		Corte corte = new Corte();
		if(!ventas.isEmpty()) {
			corte.setEfectivo(0);
			corte.setTarjeta(0);
			corte.setCredito(0);
			corte.setVales(0);
		
			for(Venta elemento: ventas) {
				switch (elemento.getTipoVenta()) {
				case 1: corte.setEfectivo(corte.getEfectivo()+elemento.getTotal());
					break;
				case 2: corte.setTarjeta(corte.getTarjeta()+elemento.getTotal());
					break;
				case 3: corte.setCredito(corte.getCredito()+elemento.getTotal());
					break;
				case 4: corte.setVales(corte.getVales()+elemento.getTotal());
					break;
				default:
					break;
				}
			}
		
			corte.setTotal(corte.getEfectivo()+corte.getTarjeta()+corte.getCredito()+corte.getVales());
			corte.setDate(fecha);
			corte.setTipoOperacion(tipoOperacion);
			return corte;
		}
		else
			return null;
	}
	
	/**
	*Guarda el corte del día en la base de Datos.
	*
	* @param LocalDate fecha: Fecha actual del sistema.
	* 		double efectivo: El total de ventas/compras que se hicieron con efectivo.
	* 		double tarjeta: El total de ventas/compras que se hicieron con tarjeta.
	* 		double credito: El total de ventas/compras que se hicieron con credito en tienda.
	* 		double vales: El total de ventas/compras que se hicieron con vales de despensa.
	* 		double total: El total de ventas/compras que se hicieron en el dia.
	* 		int tipoOperacion: Tipo de Corte (Ingresos = 1, Egresos = 2).
	* 
	* @return boolean: True si se guardo el corte o false si no.
	**/
	public boolean guardarCorte(LocalDate fecha, double efectivo, double tarjeta, double credito, double vales, double total, 
			int tipoOperacion) {
		Corte corte = new Corte();
		
		corte.setDate(fecha);
		corte.setEfectivo(efectivo);
		corte.setTarjeta(tarjeta);
		corte.setCredito(credito);
		corte.setVales(vales);
		corte.setTotal(total);
		corte.setTipoOperacion(tipoOperacion);
		
		log.info("Guardando corte...");
		try {
			corteRepository.save(corte);
			log.info("El corte ha sido agregado");
			return true;
		}
		catch (Exception ex) {
			log.info("Error al guardar el corte");
			return false;
		}
	}
	
	/**
	*Recupera el corte del día de la base de Datos
	*
	* @param LocalDate fecha: Fecha de la cual se quiere obtener el Corte.
	* 		int tipoOperacion: Tipo de Corte (Ingresos = 1, Egresos = 2).
	* 
	* @return Corte: El corte del dia.
	**/
	public Corte recuperaCorte(LocalDate fecha, int tipoOperacion){
		log.info("Recuperando corte...");
		Corte corte = new Corte ();
		corte=corteRepository.findByDateAndTipoOperacion(fecha, tipoOperacion);
		if (corte!=null)
			log.info("El corte ha sido recuperado");
		return corte;
	}
	
}

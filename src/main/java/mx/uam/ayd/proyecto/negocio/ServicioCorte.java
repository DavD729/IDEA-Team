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
	
	public Corte sumaVentas(List<Venta> ventas, LocalDate fecha, int tipoOperacion) {
		Corte corte = new Corte();
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
	
	public Corte guardarCorte(LocalDate fecha, double efectivo, double tarjeta, double credito, double vales, double total, 
			int tipoOperacion) {
		log.info("Guardando corte...");
		Corte corte = new Corte();
		corte.setDate(fecha);
		corte.setTipoOperacion(tipoOperacion);
		corte.setEfectivo(efectivo);
		corte.setTarjeta(tarjeta);
		corte.setCredito(credito);
		corte.setVales(vales);
		corte.setTotal(total);
		corte.setTipoOperacion(tipoOperacion);
		
		corteRepository.save(corte);
		log.info("El corte ha sido agregado");
		return corte;
	}
	
	public Corte recuperaCorte(LocalDate fecha, int tipoOperacion){
		Corte corte = corteRepository.findByDateAndTipoOperacion(fecha, tipoOperacion);
		return corte;
	}
	
}

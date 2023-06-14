package mx.uam.ayd.proyecto.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Service

public class ServicioVenta {
	
	@Autowired 
	private VentaRepository ventaRepository;
	/**
	*Recupera la Lista de las ventas del día.
	*
	* @param LocalDate date: fecha actual del sistema.
	* 
	* @return List<Venta>: Lista de ventas del dia.
	**/
	public List<Venta> recuperaVentas(LocalDate date) {
		List<Venta> ventas = new ArrayList<>();
		
		ventas=(List<Venta>) ventaRepository.findByDate(date);
		return ventas;
	}
}



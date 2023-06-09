package mx.uam.ayd.proyecto.negocio;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.HistorialVentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Service
public class ServicioHistorialVenta {
	
	@Autowired
	HistorialVentaRepository repositorioHistorial;
	
	public List<HistorialVenta> recuperaHitorialVentas() {
		List<HistorialVenta> historiales = new ArrayList<>();
		repositorioHistorial.findAll().forEach(historial -> {
			historiales.add(historial);
		});
		return historiales;
	}
	
	public HistorialVenta recuperaOrCreaHistorial(YearMonth fecha) {
		HistorialVenta historial = repositorioHistorial.findByFecha(fecha);
		if(historial == null) {
			historial = new HistorialVenta();
			historial.setFechaHistoria(fecha);
		}
		return historial;
	}
	
	public boolean agregaVentaDeHistorial(Venta venta, List<Producto> productosVendidos) {
		return false;
	}
}

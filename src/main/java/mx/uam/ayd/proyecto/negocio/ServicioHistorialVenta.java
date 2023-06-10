package mx.uam.ayd.proyecto.negocio;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public List<HistorialVenta> recuperaHistorialVentas() {
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
			historial.setFecha(fecha);
		}
		return historial;
	}
	
	public void agregaVentaDeHistorial(Venta venta, Map<Producto, Integer> relacionVenta) {
		YearMonth fecha = YearMonth.of(venta.getDate().getYear(), venta.getDate().getMonth());
		HistorialVenta historial = recuperaOrCreaHistorial(fecha);
		relacionVenta.forEach((producto, cantidadVendidos) -> {
			int value = historial.getProductosVendidos().getOrDefault(producto, -1);
			if(value != -1) {
				value += cantidadVendidos;
			} else {
				historial.getProductosVendidos().put(producto, cantidadVendidos);
			}
		});
	}
	
	public String[][] recuperaTablaDeDatosPorFecha(YearMonth fecha) {
		HistorialVenta historial = recuperaOrCreaHistorial(fecha);
		Map<Producto, Integer> datos = historial.getProductosVendidos();
		String[][] matrizDeDatos = new String[datos.size()][3];
		Set<Producto> productos = datos.keySet();
		Collection<Integer> vendidos = datos.values();
		for(int index = 0; index < datos.size(); index++) {
			matrizDeDatos[index][0] = ((Producto)productos.toArray()[index]).getNombre();
			matrizDeDatos[index][1] = vendidos.toArray()[index].toString();
			matrizDeDatos[index][2] = String.valueOf(((Producto)productos.toArray()[index]).getIdProducto());
		}
		return matrizDeDatos;
	}
}

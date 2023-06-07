package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class HistorialVenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHistorial;
	private LocalDate fechaHistoria;
	private final List<Producto> productosVendidos = new ArrayList<>();
	
	public boolean addVenta(Collection<Producto> vendidos) {
		return productosVendidos.addAll(vendidos);
	}
}

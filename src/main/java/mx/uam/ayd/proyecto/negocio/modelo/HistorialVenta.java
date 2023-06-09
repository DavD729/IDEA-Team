package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class HistorialVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHistorial;
	private YearMonth fechaHistoria;
	private final Map<Producto, Integer> productosVendidos = new HashMap<>();
}
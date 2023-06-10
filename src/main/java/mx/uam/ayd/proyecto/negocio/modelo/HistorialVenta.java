package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.YearMonth;
import java.util.HashMap;

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
	private YearMonth fecha;
	private final HashMap<Producto, Integer> productosVendidos = new HashMap<>();
}
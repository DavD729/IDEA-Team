package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;
	private LocalDate date;
	private int tipoVenta; // Efectivo = 1, Tarjeta de Credito/Debito = 2, Credito en tienda = 3, Vales de despensa = 4
	private double total;
}
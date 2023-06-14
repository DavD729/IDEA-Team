package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Corte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCorte;
	private LocalDate date;
	private int tipoOperacion; //  Ingresos = 1, Egresos = 2
	private double efectivo;
	private double tarjeta;
	private double credito;
	private double vales;
	private double total;
}
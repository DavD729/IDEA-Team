package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data

public class Corte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCorte;
	private LocalDate date;
	private int tipoOperacion;
	private double Efectivo;
	private double Tarjeta;
	private double Credito;
	private double Vales;
	private double Total;
	
	@OneToMany(targetEntity = Corte.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idCorte")
	private final List <Corte> cortes = new ArrayList <> ();
	
	public boolean addCorte(Corte corte) {
		return cortes.add(corte);
	}
}
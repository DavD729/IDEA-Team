package mx.uam.ayd.proyecto.negocio.modelo;

import java.sql.Timestamp;
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
public class OrdenOnline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOrdenOnline;
	private int estado;
	private Timestamp horaApertura;
	private Timestamp horaCierre;
	private float total;
	@OneToMany(targetEntity = OrdenProducto.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idOrdenOnline")
	private final List <OrdenProducto> ordenProducto = new ArrayList <> ();
	public static final int PEDIDA = 0;
	public static final int ENTREGADA = 1;

	public OrdenOnline(){
		//
	}

	public OrdenOnline(int estado, Timestamp horaApertura, float total) {
		super();
		this.estado = estado;
		this.horaApertura = horaApertura;
		this.total = total;
	}

	public boolean addOrdenProducto(OrdenProducto o) {

		if(o == null) {
			throw new IllegalArgumentException("La instancia de ordenProducto no puede ser null");
		}

		if(ordenProducto.contains(o)) {
			// Checo si la ordenProducto está en la OrdenOnline por que no se puede agregar una ordenProducto dos veces
			return false;
		}

		return ordenProducto.add(o);
	}

	public List <OrdenProducto> recuperaListaOrdenProducto(){
		return ordenProducto;
	}
}


package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class RelacionProductoValor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRelacion;
	private long producto;
	private int cantidadVendida;
	private long relacion;
}

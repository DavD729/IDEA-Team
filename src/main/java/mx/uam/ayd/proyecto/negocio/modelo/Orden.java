package mx.uam.ayd.proyecto.negocio.modelo;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOrden; 
	private String producto;
	private int estado;
	private Timestamp horaApertura;
	private Timestamp horaCierre;
	private double costoUnitario;
	
	public static final int PEDIDA = 0;
	public static final int LISTA = 1;
	public static final int ENTREGADA = 2;
	public static final int PAGADA = 3;

	public Orden(){
		//
	}

	public Orden(String producto, Timestamp  hrApertura, double costoUnitario) {
		super();
		this.producto = producto;
		this.horaApertura = hrApertura;
		this.costoUnitario = costoUnitario;
	}
	
	public Orden( Timestamp  hrApertura, double costoUnitario, int estado) {//will
		super();
		
		this.horaApertura = hrApertura;
		this.estado = estado;
		this.costoUnitario = costoUnitario;
	}

	public Orden(String producto, Timestamp horaApertura, Timestamp horaCierre, double costoUnitario, int estado) {
		super();
		this.producto = producto;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.estado = estado;
		this.costoUnitario = costoUnitario;
	}


	public Orden(String producto, Timestamp horaApertura, double costoUnitario, int estado) {
		this.producto = producto;
		this.horaApertura = horaApertura;
		this.costoUnitario = costoUnitario;
		this.estado = estado;
	}
	
	
}

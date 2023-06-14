package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Data
public class Tiempo {
	/*Atributos de la entidad Tiempo*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTiempo;
	private long idEmpleado;
	private String anio;
	private String mes;
	private String dia;
	private String hora;
	private String min;
	private String segundos;	
}

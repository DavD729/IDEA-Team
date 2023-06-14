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

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
public class Empleado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	/*Atributos de la entidad Empleto*/
	private long idEmpleado;
	private long idEntrada;
	private long idSalida;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private String direccion;
	private String tel;
	private String email;
	private String tarea;		
}

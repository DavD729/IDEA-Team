package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;


@Entity
@Data
@Getter
@Setter
public class Empleado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//Atributos de la entidad Empleto
	private long idEmpleado;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private String direccion;
	private String tel;
	private String email;
	private String tarea;	
}

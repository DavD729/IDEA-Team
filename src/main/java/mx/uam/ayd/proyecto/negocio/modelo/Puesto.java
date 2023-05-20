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
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Puesto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPuesto;
	
	private String nombre;
	
	@OneToMany(targetEntity=Empleado.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="idPuesto")
	//Recupera la lista de Empleados agregados en la base de Datos
	private final List<Empleado> empleados=new ArrayList <> ();
	
	//Valida que no se este agragando un empleado sin datos
	public boolean addEmpleado(Empleado empleado) {
		if(empleado==null) {
			throw new IllegalArgumentException("El usuario no puede ser null ");	
		}
		
		if(empleados.contains(empleado)) {
			//Checa si el empleado esta en el grupo
			return false;
			}
		return empleados.add(empleado);
	}
	

}
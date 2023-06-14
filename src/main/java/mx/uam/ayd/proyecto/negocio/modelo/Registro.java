package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.Calendar;
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
public class Registro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*Atributos*/
	private long idRegistro;
	private String nombre;

	/*Crea el atributo Registro en la tabla de la entidad Tiempo*/
	@OneToMany(targetEntity=Tiempo.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)	
	@JoinColumn(name="idRegistro")

			private final List<Tiempo> tiempos=new ArrayList <> ();
		
		/*Valida que no se este agragando un tiempo en el Registro*/
		public boolean addTiempo(Tiempo tiempo) {
			if(tiempo==null) {
				throw new IllegalArgumentException("El usuario no puede ser null ");	
			}
			
			return tiempos.add(tiempo);
		}

}

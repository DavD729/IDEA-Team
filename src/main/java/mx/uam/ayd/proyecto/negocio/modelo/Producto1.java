package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity
@Data
public class Producto1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	private String nombre;
	private float precio;
	private float ePrecio;
	private float tipo;
	private String imagen;
	private boolean onlineEnabled;
	private String comentario;
	@OneToMany(targetEntity = OrdenProducto.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idProducto")

	@LazyCollection(LazyCollectionOption.FALSE)
	private final List <OrdenProducto> ordenProducto = new ArrayList <> ();

	@OneToMany(targetEntity = Orden.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idProducto")

	@LazyCollection(LazyCollectionOption.FALSE)
	private final List <Orden> ordenListaProducto = new ArrayList <> ();

	public Producto1() {

	}
	
	public Producto1(String nombre, float tipo, float precio, float ePrecio, String imagen, boolean onlineEnabled,
			String comentario) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
		this.ePrecio = ePrecio;
		this.imagen = imagen;
		this.onlineEnabled = onlineEnabled;
		this.comentario = comentario;
	}

	public boolean addOrdenProducto(OrdenProducto o) {

		if(o == null) {
			throw new IllegalArgumentException("La instancia de ordenProducto no puede ser null");
		}

		if(ordenProducto.contains(o)) {
			// Checo si la ordenProducto está en el Producto por que no se puede agregar una ordenProducto dos veces
			return false;
		}

		return ordenProducto.add(o);
	}

	/**
	 * Añade una orden a la lista ordenList si no está en ella o regresa excepción si recibe null.
	 * @param o una orden.
	 * @return true si añade la orden o false en caso contrario.
	 * @throws IllegalArgumentException
	 */
	public boolean agregaAOrdenLista(Orden o) throws IllegalArgumentException{

		if(o == null) {
			throw new IllegalArgumentException("La instancia de orden no puede ser null");
		}

		if(ordenListaProducto.contains(o)) {
			// Checo si la orden está en la lista porque no se puede agregar una orden dos veces.
			return false;
		}

		return ordenListaProducto.add(o);
	}

	public List <OrdenProducto> recuperaListaOrdenProducto(){
		return ordenProducto;
	}

	/**
	 * Recupera la lista de ordenes en las que está el producto.
	 * @return lista de ordenes en las que está el producto.
	
	 */
	public List <Orden> recuperaListaOrdenLista(){
		return ordenListaProducto;
	}

	/**
	 * recupera atributo onlineEnabled.
	 * @return onlineEnabled atributo.
	
	 */
	public boolean getOnlineEnabled(){
		return this.onlineEnabled;
	}

}


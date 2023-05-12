package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategoria;
	private String nombre;
	
	@OneToMany(targetEntity = Categoria.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idCategoria")
	private final List<Categoria> categorias = new ArrayList<>();
	
	public boolean addCategoria(Categoria categoria) {
		Objects.requireNonNull(categoria, "La categoria no puede ser nula");
		if(categorias.contains(categoria)) return false;
		return categorias.add(categoria);
	}
}

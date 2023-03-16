package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;

@Slf4j
@Service
public class ServicioCategoria {
	
	@Autowired
	CategoriaRepository repositorioCat;
	
	public List<Categoria> recuperaCategorias() {
		List<Categoria> categorias = new ArrayList<>();
		repositorioCat.findAll().forEach((categoria) -> {
			categorias.add(categoria);
		});
		return categorias;
	}
	
	public Categoria registraCategoria(String nombre) {
		Categoria categoria = repositorioCat.findByNombre(nombre);
		if(categoria != null) {
			throw new IllegalArgumentException("La categoria dada ya se encuentra registrada");
		}
		categoria = new Categoria();
		categoria.setNombre(nombre);
		repositorioCat.save(categoria);
		log.info("La categoria {} ha sido agregada", nombre);
		return categoria;
	}

	@Nullable
	public Categoria dameCategoria(String nombre) {
		return repositorioCat.findByNombre(nombre);
	}
}

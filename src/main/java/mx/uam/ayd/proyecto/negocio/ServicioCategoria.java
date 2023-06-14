package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;

/**
 * Servicio utilizado para manipular los datos del repositorio "Categoria"
 * 
 * @author David
 */

@Slf4j
@Service
public class ServicioCategoria {
	
	@Autowired
	CategoriaRepository repositorioCategoria;
	
	/**
	 * Solicita al repositorio una lista con las categorias registradas en la base de datos
	 * 
	 * @return Lista con todas las categorias registradas
	 */
	
	public List<Categoria> recuperaCategorias() {
		List<Categoria> categorias = new ArrayList<>();
		repositorioCategoria.findAll().forEach((categoria) -> {
			categorias.add(categoria);
		});
		return categorias;
	}
	
	/**
	 * Prepara y solicita al repositorio el registro de una nueva categoria
	 * 
	 * @param nombre Nombre de la categoria a ser registrada
	 * @return Categoria instanciada y registrada propiamente
	 */
	
	public Categoria registraCategoria(String nombre) {
		Categoria categoria = repositorioCategoria.findByNombre(nombre);
		if(categoria != null) {
			throw new IllegalArgumentException("La categoria dada ya se encuentra registrada");
		}
		categoria = new Categoria();
		categoria.setNombre(nombre);
		repositorioCategoria.save(categoria);
		log.info("La categoria {} ha sido agregada", nombre);
		return categoria;
	}
	
	/**
	 * Solicita al repositorio la busqueda de una categoria usando su nombre
	 * 
	 * @param nombre Nombre de la categoria a buscar
	 * @return Categoria registrada en la base de datos, o bien un {@literal null} si no se encuentra
	 */
	
	@Nullable
	public Categoria dameCategoria(String nombre) {
		return repositorioCategoria.findByNombre(nombre);
	}
}

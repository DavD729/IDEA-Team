package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.presentacion.inicio.ControlInicio;

@SpringBootApplication
public class TiendaAplicacion {
	
	@Autowired
	ControlInicio controlInicio;
	
	@Autowired
	CategoriaRepository categoriaRepositorio;
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TiendaAplicacion.class);
		builder.headless(false);
		builder.run(args);
	}
	
	@PostConstruct
	public void inicio() {
		this.inicializaBD();
		controlInicio.inicio();
	}
	
	/**
	 *  Aqui se inicializan todos los datos que se requieran de Base
	 */
	public void inicializaBD() {
		/**
		 * Se inicia la base de datos con una categoria de uso utilitario llamada "Otra".
		 * Ésta categoría nos ayudará con el proceso de registro de productos
		 */
		
		Categoria categoriaOtra = new Categoria();
		categoriaOtra.setNombre("Otra");
		categoriaRepositorio.save(categoriaOtra);
	}
}

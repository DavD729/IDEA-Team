package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.datos.PuestoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.presentacion.inicio.ControlInicio;

@SpringBootApplication
public class TiendaAplicacion {
	
	/**
	 *  Control principal del programa "Menú"
	 */
	@Autowired
	ControlInicio controlInicio;
	
	/**
	 *  Repositorio de Categoria usado para inicializar datos utilitarios
	 */
	@Autowired
	CategoriaRepository categoriaRepositorio;
	
	@Autowired
	PuestoRepository puestoRepository;
	
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
	 *  Aqui se inicializan todos los datos que se requieran de Base de forma Utilitaria
	 */
	public void inicializaBD() {
		Categoria categoriaOtra = new Categoria();
		categoriaOtra.setNombre("Otra");
		categoriaRepositorio.save(categoriaOtra);
		
		Puesto puestoAdmin = new Puesto();
		puestoAdmin.setNombre("Administrador");
		puestoRepository.save(puestoAdmin);
		
		Puesto puestoVendedor = new Puesto();
		puestoVendedor.setNombre("Vendedor");
		puestoRepository.save(puestoVendedor);
	}
}

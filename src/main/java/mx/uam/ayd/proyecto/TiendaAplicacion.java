package mx.uam.ayd.proyecto;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.datos.PuestoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
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
	
	@Autowired
	VentaRepository ventaRepository;
	
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
		
		//Inicia la inicialización de la BD para ventas
		LocalDate Fecha = LocalDate.now();
		
		Venta venta1 = new Venta();
		venta1.setDate(Fecha);
		venta1.setTipoVenta(1);
		venta1.setTotal(59.5);
		ventaRepository.save(venta1);
		
		Venta venta2 = new Venta();
		venta2.setDate(Fecha);
		venta2.setTipoVenta(2);
		venta2.setTotal(542.3);
		ventaRepository.save(venta2);
		
		Venta venta3 = new Venta();
		venta3.setDate(Fecha);
		venta3.setTipoVenta(1);
		venta3.setTotal(78.62);
		ventaRepository.save(venta3);
		//Finaliza la inicialización de la BD para ventas
	}
}

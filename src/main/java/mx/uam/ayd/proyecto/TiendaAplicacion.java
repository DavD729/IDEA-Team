package mx.uam.ayd.proyecto;

import java.time.LocalDate;
import java.time.YearMonth;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.datos.HistorialVentaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.PuestoRepository;
import mx.uam.ayd.proyecto.datos.RegistroRepository;
import mx.uam.ayd.proyecto.datos.RelacionVentaProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.negocio.modelo.RelacionProductoValor;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.inicio.ControlInicio;

@SpringBootApplication
public class TiendaAplicacion {
	
	@Autowired
	ControlInicio controlInicio;
	
	@Autowired
	CategoriaRepository categoriaRepositorio;
	
	@Autowired
	ProductoRepository productoRepositorio;
	
	@Autowired
	PuestoRepository puestoRepository;
	
	@Autowired
	RegistroRepository registroRepository;
	
	@Autowired
	VentaRepository ventaRepository;
	
	@Autowired
	HistorialVentaRepository historialVentaRepository;
	
	@Autowired
	RelacionVentaProductoRepository ProductoVentaRepository;
	
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
		
		Categoria comestibles = new Categoria();
		comestibles.setNombre("Comestibles");
		categoriaRepositorio.save(comestibles);
		
		Categoria vegetales = new Categoria();
		vegetales.setNombre("Vegetales");
		categoriaRepositorio.save(vegetales);
		
		Categoria juguetes = new Categoria();
		juguetes.setNombre("Juguetes");
		categoriaRepositorio.save(juguetes);
		
		Categoria lineaBlanca = new Categoria();
		lineaBlanca.setNombre("Linea Blanca");
		categoriaRepositorio.save(lineaBlanca);
		
		Categoria electronica = new Categoria();
		electronica.setNombre("Electronica");
		categoriaRepositorio.save(electronica);
		
		Categoria electrodomesticos = new Categoria();
		electrodomesticos.setNombre("Electrodomesticos");
		categoriaRepositorio.save(electrodomesticos);
		
		Producto prod1 = new Producto("Papas Fritas", 17.00F, 200);
		prod1.setCategoria("Comestibles");
		prod1.setDescripcion("Sabritas - Saladas, 50g");
		productoRepositorio.save(prod1);
		
		Producto prod2 = new Producto("Jugo", 21.90F, 330);
		prod2.setCategoria("Comestibles");
		prod2.setDescripcion("Jumex - Naranja, 100ml");
		productoRepositorio.save(prod2);
		
		Producto prod3 = new Producto("Cartón de Leche", 25.50F, 115);
		prod3.setCategoria("Comestibles");
		prod3.setDescripcion("NutriLeche, 250ml");
		productoRepositorio.save(prod3);
		
		Producto prod4 = new Producto("Lechuga", 12.00F, 30);
		prod4.setCategoria("Vegetales");
		prod4.setDescripcion("VegeNat, 100g");
		productoRepositorio.save(prod4);
		
		Producto prod5 = new Producto("Monopoly", 350.00F, 20);
		prod5.setCategoria("Juguetes");
		prod5.setDescripcion("Hasbro, +12");
		productoRepositorio.save(prod5);
		
		Producto prod6 = new Producto("Uno", 150.00F, 34);
		prod6.setCategoria("Juguetes");
		prod6.setDescripcion("Hasbro, +8");
		productoRepositorio.save(prod6);
		
		Producto prod7 = new Producto("Refrigerador", 8150.00F, 4);
		prod7.setCategoria("Linea Blanca");
		prod7.setDescripcion("Whirpool, Garantia 2 años");
		productoRepositorio.save(prod7);
		
		Producto prod8 = new Producto("Refrigerador", 11550.00F, 9);
		prod8.setCategoria("Linea Blanca");
		prod8.setDescripcion("LG, Garantia 3 años");
		productoRepositorio.save(prod8);
		
		Producto prod9 = new Producto("Pantalla Plana", 18000.00F, 10);
		prod9.setCategoria("Electronica");
		prod9.setDescripcion("SHARP, RGB, 50\", Garantia 1 años");
		productoRepositorio.save(prod9);
		
		Producto prod10 = new Producto("Cafetera", 1500.00F, 35);
		prod10.setCategoria("Electrodomesticos");
		prod10.setDescripcion("Cusinart, 5lt, Garantia 6 meses");
		productoRepositorio.save(prod10);
		
		HistorialVenta historial = new HistorialVenta();
		historial.setFecha(YearMonth.of(2023, 5));
		historialVentaRepository.save(historial);
		
		RelacionProductoValor relacionA = new RelacionProductoValor();
		relacionA.setProducto(1l);
		relacionA.setCantidadVendida(23);
		relacionA.setRelacion(historial.getIdHistorial());
		ProductoVentaRepository.save(relacionA);
		
		RelacionProductoValor relacionB = new RelacionProductoValor();
		relacionB.setProducto(5l);
		relacionB.setCantidadVendida(3);
		relacionB.setRelacion(historial.getIdHistorial());
		ProductoVentaRepository.save(relacionB);
		
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

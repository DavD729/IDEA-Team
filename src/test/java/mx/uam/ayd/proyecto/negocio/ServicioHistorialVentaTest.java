package mx.uam.ayd.proyecto.negocio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import mx.uam.ayd.proyecto.datos.HistorialVentaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.RelacionVentaProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.HistorialVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.RelacionProductoValor;

/**
 * Esta clase contendra los casos de prueba para los Metodos de "Servicio Historial de Venta"
 * 
 * @author David
 */

@RunWith(MockitoJUnitRunner.class)
class ServicioHistorialVentaTest {
	
	@Mock
	private ProductoRepository repositorioProducto;
	
	@Mock
	private HistorialVentaRepository repositorioHistorial;
	
	@Mock
	private RelacionVentaProductoRepository repositorioProductoVenta;
	
	@InjectMocks
	private ServicioProducto servicioProducto;
	
	@InjectMocks
	private ServicioHistorialVenta servicioHistorial;
	
	@InjectMocks
	private ServicioRelacionProductoValor servicioRelacion;
	
	private List<HistorialVenta> listaHistorialesPrueba;
	private List<RelacionProductoValor> listaRelacionesPrueba;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		listaHistorialesPrueba = new ArrayList<>();
		listaRelacionesPrueba = new ArrayList<>();
	}
	
	/**
	 * Prueba JUnit 1 - Metodo a Probar "recuperaHistorial".
	 * 
	 * 
	 */
	
	@Test
	void testRecuperaHistorial() {
		//Caso 1 - Recuperación en repositorio Vacio
		
		//Preparación de Datos
		when(repositorioHistorial.findByFecha(any())).thenReturn(null);
		
		// Llamada al método bajo prueba
		HistorialVenta historial = servicioHistorial.recuperaHistorial(YearMonth.now());
		
		//Verificación de datos del Repositorio
		assertEquals(null, historial);
		
		//Caso 2 - Recuperación en repositorio con un Elemento
		
		//Preparación de Datos
		HistorialVenta historialPrueba = new HistorialVenta();
		historialPrueba.setFecha(YearMonth.now());
		when(repositorioHistorial.findByFecha(YearMonth.now())).thenReturn(historialPrueba);
				
		// Llamada al método bajo prueba
		historial = repositorioHistorial.findByFecha(YearMonth.now());
				
		//Verificación de datos del Repositorio
		assertEquals(YearMonth.now(), historial.getFecha());
		
		//Caso 3 - Recuperación en repositorio con 3 Elementos
		
		//Preparación de Datos
		HistorialVenta historialA = new HistorialVenta();
		HistorialVenta historialB = new HistorialVenta();
		HistorialVenta historialC = new HistorialVenta();
		historialA.setFecha(YearMonth.of(2023, 6));
		historialB.setFecha(YearMonth.of(2023, 5));
		historialC.setFecha(YearMonth.of(2023, 4));
		
		listaHistorialesPrueba.add(historialA);
		listaHistorialesPrueba.add(historialB);
		listaHistorialesPrueba.add(historialC);
		
		when(repositorioHistorial.findByFecha(YearMonth.of(2023, 4))).thenReturn(historialC);
		when(repositorioHistorial.findAll()).thenReturn(listaHistorialesPrueba);
		
		// Llamada al método bajo prueba
		historialPrueba = servicioHistorial.recuperaHistorial(YearMonth.of(2023, 4));
		
		//Verificación de datos del Repositorio
		assertEquals(YearMonth.of(2023, 4), historialPrueba.getFecha());
		assertEquals(3, listaHistorialesPrueba.size());
	}
	
	/**
	 * Prueba JUnit 2 - Metodo a Probar "creaHistorial".
	 * 
	 * 
	 */
	
	@Test
	void testCreaHistorial() {
		//Caso 1 - Registro de Historial en repositorio Vacio
		
		//Preparación de Datos
		when(repositorioHistorial.findByFecha(any())).thenReturn(null);
		when(repositorioHistorial.findAll()).thenReturn(listaHistorialesPrueba);
		
		// Llamada al método bajo prueba
		HistorialVenta historial = servicioHistorial.creaHistorial(YearMonth.now());
		listaHistorialesPrueba.add(historial);
		
		//Verificación de datos del Repositorio
		assertEquals(YearMonth.now(), historial.getFecha());
		
		//Caso 2 - Registro de Historial en repositorio con un Elemento
		
		//Preparación de Datos
		HistorialVenta historialPrueba = new HistorialVenta();
		historialPrueba.setFecha(YearMonth.now());
		when(repositorioHistorial.findByFecha(YearMonth.now())).thenReturn(historialPrueba);
				
		// Llamada al método bajo prueba
		historial = repositorioHistorial.findByFecha(YearMonth.now());
				
		//Verificación de datos del Repositorio
		assertEquals(historialPrueba.getFecha(), historial.getFecha());
		assertEquals(true, listaHistorialesPrueba.contains(historial));
		
		//Caso 2 - Registro de Historial ya existente en repositorio
		
		//Preparación de Datos
		HistorialVenta historialPruebaB = new HistorialVenta();
		historialPruebaB.setFecha(YearMonth.now());
		when(repositorioHistorial.findByFecha(YearMonth.now())).thenReturn(historialPrueba);
		
		// Llamada al método bajo prueba
		assertThrows(IllegalArgumentException.class, () -> {
			servicioHistorial.creaHistorial(YearMonth.now());	
		});
	}
	
	/**
	 * Prueba JUnit 3 - Metodo a Probar "agregaVentaDeHistorial".
	 * 
	 * 
	 */
	
	@Test
	void testAgregaVentaDeHistorial() {
		//Caso 1 - Registro de Venta al Historial en repositorio sin historial Existente
		
		//Preparación de Datos
		when(repositorioHistorial.findByFecha(any())).thenReturn(null);
		
		Producto producto = new Producto();
		producto.setIdProducto(1);
		
		// Llamada al método bajo prueba
		assertThrows(IllegalArgumentException.class, () -> {
			servicioHistorial.agregaVentaDeHistorial(YearMonth.now(), producto, 13);
		});
		
		//Caso 2 - Registro de Venta al Historial en repositorio con historial Existente
		
		//Preparación de Datos
		HistorialVenta historialPrueba = new HistorialVenta();
		historialPrueba.setFecha(YearMonth.now());
		
		RelacionProductoValor relacion = new RelacionProductoValor();
		List<RelacionProductoValor> ventas = new ArrayList<>();
		ventas.add(relacion);
		
		when(repositorioHistorial.findByFecha(historialPrueba.getFecha())).thenReturn(historialPrueba);
		
		listaRelacionesPrueba.add(relacion);
		// Llamada al método bajo prueba
		servicioHistorial.agregaVentaDeHistorial(YearMonth.now(), producto, 13);
		
		//Verificación de datos del Repositorio
		assertEquals(1, listaRelacionesPrueba.size());
	}
	
	/**
	 * Prueba JUnit 4 - Metodo a Probar "recuperaTablaDeDatosPorFecha".
	 * 
	 * 
	 */
	
	@Test
	void testRecuperaTablaDeDatosPorFecha() {
		//Caso 1 - Solicitud de tabla al repositorio sin historial Existente
		
		//Preparación de Datos
		when(repositorioHistorial.findByFecha(any())).thenReturn(null);
		
		HistorialVenta historial = new HistorialVenta();
		historial.setFecha(YearMonth.now());
		List<RelacionProductoValor> ventas = servicioRelacion.recuperaVentas(historial);
		
		// Llamada al método bajo prueba
		String[][] tabla = servicioHistorial.recuperaTablaDeDatosPorFecha(YearMonth.now());
		
		int filas = listaRelacionesPrueba.size();
		int columnas = 3;
		String[][] matrizEsperada = new String[filas][columnas];
		
		for (int i = 0; i < filas; i++) {
			Producto producto = repositorioProducto.findById(ventas.get(i).getProducto()).get();
			matrizEsperada[i][0] = producto.getNombre();
			matrizEsperada[i][1] = String.valueOf(ventas.get(i).getCantidadVendida());
			matrizEsperada[i][2] = String.valueOf(producto.getIdProducto());
		}
		
		//Verificación de datos del Repositorio
		assertArrayEquals(tabla, matrizEsperada);
		
		//Caso 2 - Solicitud de tabla al repositorio con historial Existente sin Ventas
		
		//Preparación de Datos
		HistorialVenta historialPrueba = new HistorialVenta();
		historialPrueba.setFecha(YearMonth.now());
		
		when(repositorioHistorial.findByFecha(historialPrueba.getFecha())).thenReturn(historialPrueba);
		
		// Llamada al método bajo prueba
		tabla = servicioHistorial.recuperaTablaDeDatosPorFecha(YearMonth.now());
				
		filas = listaRelacionesPrueba.size();
		columnas = 3;
		matrizEsperada = new String[filas][columnas];
				
		for (int i = 0; i < filas; i++) {
			Producto producto = repositorioProducto.findById(ventas.get(i).getProducto()).get();
			matrizEsperada[i][0] = producto.getNombre();
			matrizEsperada[i][1] = String.valueOf(ventas.get(i).getCantidadVendida());
			matrizEsperada[i][2] = String.valueOf(producto.getIdProducto());
		}
		
		//Verificación de datos del Repositorio
		assertArrayEquals(tabla, matrizEsperada);
		
		//Caso 3 - Solicitud de tabla al repositorio con historial Existente con Ventas
		
		//Preparación de Datos
		historialPrueba = new HistorialVenta();
		historialPrueba.setFecha(YearMonth.now());
		
		Producto productoA = new Producto();
		productoA.setNombre("Producto 1");
		productoA.setCategoria("Categoría 1");
		productoA.setEnExistencia(10);
		productoA.setPrecio(9.99);
		productoA.setDescripcion("Descripción del producto");
		productoA.setIdProducto(1);
		
		when(repositorioHistorial.findByFecha(YearMonth.now())).thenReturn(historialPrueba);
		when(repositorioProducto.findById(any())).thenReturn(Optional.of(productoA));
		when(repositorioProductoVenta.findByRelacion(historial.getIdHistorial())).thenReturn(listaRelacionesPrueba);
		
		RelacionProductoValor relacionA = new RelacionProductoValor();
		relacionA.setProducto(1);
		relacionA.setCantidadVendida(10);
		
		ventas = listaRelacionesPrueba;
		ventas.add(relacionA);
		
		// Llamada al método bajo prueba
		tabla = servicioHistorial.recuperaTablaDeDatosPorFecha(YearMonth.now());
		
		filas = listaRelacionesPrueba.size();
		columnas = 3;
		matrizEsperada = new String[filas][columnas];
		
		for (int i = 0; i < filas; i++) {
			Producto producto = repositorioProducto.findById(ventas.get(i).getProducto()).get();
			matrizEsperada[i][0] = producto.getNombre();
			matrizEsperada[i][1] = String.valueOf(ventas.get(i).getCantidadVendida());
			matrizEsperada[i][2] = String.valueOf(producto.getIdProducto());
		}
		
		//Verificación de datos del Repositorio
		assertArrayEquals(tabla, matrizEsperada);
	}
}

package mx.uam.ayd.proyecto.negocio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Esta clase contendra los casos de prueba para los Metodos de "Servicio Producto"
 * 
 * @author David, Ian
 */

@RunWith(MockitoJUnitRunner.class)
class ServicioProductoTest {
	
	@Mock
	private ProductoRepository productoRepository;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@InjectMocks
	private ServicioProducto servicioProducto;
	
	@InjectMocks
	private ServicioCategoria servicioCategoria;
	
	private List<Producto> listaProductosPrueba;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		listaProductosPrueba = new ArrayList<>();
	}
	
	/**
	 * Prueba JUnit 1 - Metodo a Probar "recuperaProductos".
	 * 
	 * Este caso revisa que al solicitar una lista de productos registrados a un repositorio vacio
	 * se devolvera una lista vacia de productos
	 */
	
	@Test
	void testRecuperaVacio() {
		when(productoRepository.findAll()).thenReturn(listaProductosPrueba);
		
		List<Producto> productosRegistrados = servicioProducto.recuperaProductos();
		
		assertEquals(0, productosRegistrados.size());
		
		verify(productoRepository, times(1)).findAll();
	}
	
	/**
	 * Prueba JUnit 2 - Metodo a Probar "recuperaProductos".
	 * 
	 * Este caso revisa que al solicitar una lista de productos registrados a un repositorio con elementos
	 * se devolvera una lista con la cantidad de productos registrados en ella
	 */
	
	@Test
	void testRecuperaConElementos() {
		Producto prodA = new Producto();
		Producto prodB = new Producto();
		Producto prodC = new Producto();
		Producto prodD = new Producto();
		
		listaProductosPrueba.add(prodA);
		listaProductosPrueba.add(prodB);
		listaProductosPrueba.add(prodC);
		listaProductosPrueba.add(prodD);
		
		when(productoRepository.findAll()).thenReturn(listaProductosPrueba);
		
		List<Producto> productosRegistrados = servicioProducto.recuperaProductos();
		
		assertEquals(4, productosRegistrados.size());
		
		verify(productoRepository, times(1)).findAll();
	}
	
	/**
	 * Prueba JUnit 3 - Metodo a Probar "recuperaTablaDeProductos".
	 * 
	 * 
	 * 
	 */
	
	@Test
	void testRecuperaTablaVacia() {
		when(productoRepository.findAll()).thenReturn(listaProductosPrueba);
		
		Object[][] matrizDeDatos = servicioProducto.recuperaTablaDeProductos();
		int filas = listaProductosPrueba.size();
		int columnas = 5;
		Object[][] matrizEsperada = new Object[filas][columnas];
		
		for (Producto producto : listaProductosPrueba) {
			for (int i = 0; i < filas; i++) {
				matrizEsperada[i][0] = producto.getIdProducto();
				matrizEsperada[i][1] = producto.getNombre();
				matrizEsperada[i][2] = producto.getEnExistencia();
				matrizEsperada[i][3] = producto.getCategoria();
				matrizEsperada[i][4] = producto.getPrecio();
			}
		}
		
		assertArrayEquals(matrizDeDatos, matrizEsperada);
	}
	
	/**
	 * Prueba JUnit 4 - Metodo a Probar "recuperaTablaDeProductos".
	 * 
	 * 
	 * 
	 */
	
	@Test
	void testRecuperaTablaConElementos() {
		Producto prodA = new Producto();
		Producto prodB = new Producto();
		listaProductosPrueba.add(prodA);
		listaProductosPrueba.add(prodB);
		
		when(productoRepository.findAll()).thenReturn(listaProductosPrueba);
		
		Object[][] matrizDeDatos = servicioProducto.recuperaTablaDeProductos();
		int filas = listaProductosPrueba.size();
		int columnas = 5;
		Object[][] matrizEsperada = new Object[filas][columnas];
		
		for (Producto producto : listaProductosPrueba) {
			for (int i = 0; i < filas; i++) {
				matrizEsperada[i][0] = producto.getIdProducto();
				matrizEsperada[i][1] = producto.getNombre();
				matrizEsperada[i][2] = producto.getEnExistencia();
				matrizEsperada[i][3] = producto.getCategoria();
				matrizEsperada[i][4] = producto.getPrecio();
			}
		}
		
		assertArrayEquals(matrizDeDatos, matrizEsperada);
	}
	
	/**
	 * Prueba JUnit 5 - Metodo a Probar "agregaProducto".
	 * 
	 * 
	 */
	
	@Test
	void testAgregaProducto() {
		String nombre = "Producto 1";
		String categoria = "Categoría 1";
		int cantidad = 10;
		double precio = 9.99;
		String descripcion = "Descripción del producto";
		
		Categoria categoriaPrueba = new Categoria();
		when(categoriaRepository.findByNombre(categoria)).thenReturn(categoriaPrueba);
		
		Producto productoPrueba = servicioProducto.agregaProducto(nombre, categoria, cantidad, precio, descripcion);
		assertEquals(nombre, productoPrueba.getNombre());
		assertEquals(categoria, productoPrueba.getCategoria());
		assertEquals(cantidad, productoPrueba.getEnExistencia());
		assertEquals(precio, productoPrueba.getPrecio(), 0.001);
		assertEquals(descripcion, productoPrueba.getDescripcion());
		
		verify(categoriaRepository, times(1)).findByNombre(categoria);
		verify(productoRepository, times(1)).save(productoPrueba);
	}
	
	/**
	 * Prueba JUnit 6 - Metodo a Probar "agregaProducto".
	 * 
	 * 
	 */
	
	@Test
	public void testAgregaProductoThrowsException() {
		String nombre = "Producto 2";
		String categoria = "Categoría Inválida";
		int cantidad = 5;
		double precio = 19.99;
		String descripcion = "Descripción del producto";
		
		when(categoriaRepository.findByNombre(categoria)).thenReturn(null);
		
		assertThrows(IllegalArgumentException.class, () -> {
			servicioProducto.agregaProducto(nombre, categoria, cantidad, precio, descripcion);
		});
	}
	
	/**
	 * Prueba JUnit 7 - Metodo a Probar "buscaExistenciasDeProducto".
	 * 
	 * 
	 */
	
	@Test
	public void testBuscaExistenciasDeProducto() {
		List<Producto> expected = new ArrayList<>();
		
		String nombre = "Producto";
		Producto producto1 = new Producto(nombre, 10.99F, 10);
		Producto producto2 = new Producto(nombre, 81.50F, 5);
		
		listaProductosPrueba.add(producto1);
		listaProductosPrueba.add(producto2);
		expected.add(producto1);
		expected.add(producto2);
		
		when(productoRepository.findByNombre(nombre)).thenReturn(listaProductosPrueba);
		
		List<Producto> resultado = servicioProducto.buscaExistenciasDeProducto(nombre);
		
		assertEquals(listaProductosPrueba, resultado);
		
		Producto producto3 = new Producto("Producto 2", 44.50F, 15);
		listaProductosPrueba.add(producto3);
		
		when(productoRepository.findByNombre(nombre)).thenReturn(expected);
		
		resultado = servicioProducto.buscaExistenciasDeProducto(nombre);
		assertNotEquals(listaProductosPrueba, resultado);
		
		verify(productoRepository, times(2)).findByNombre(nombre);
	}
	
	/**
	 * Prueba JUnit 8 - Metodo a Probar "acualizaProductos".
	 * 
	 * 
	 */
	
	@Test
	public void testAcualizaProductos() {
		List<Producto> expected = new ArrayList<>();
		
		Producto prodA = new Producto("Producto A", 10.99F, 10);
		Producto prodB = new Producto("Producto B", 81.50F, 5);
		
		listaProductosPrueba.add(prodA);
		listaProductosPrueba.add(prodB);
		
		Producto prodC = new Producto("Producto A", 10.99F, 35);
		Producto prodD = new Producto("Producto B", 81.50F, 55);
		
		doNothing().when(productoRepository).deleteAll();
		
		expected.add(prodC);
		expected.add(prodD);
		
		when(productoRepository.saveAll(listaProductosPrueba)).thenReturn(expected);
		
		servicioProducto.acualiza(listaProductosPrueba);
		
		assertEquals(expected.size(), listaProductosPrueba.size());
	}
}

package mx.uam.ayd.proyecto.negocio.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import mx.uam.ayd.proyecto.negocio.ServicioCategoria;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
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
}

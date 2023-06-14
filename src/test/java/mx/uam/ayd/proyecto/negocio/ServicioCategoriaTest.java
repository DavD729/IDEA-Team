package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
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
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;

/**
 * Esta clase contendra los casos de prueba para los Metodos de "Servicio Categoria"
 * 
 * @author David
 */

@RunWith(MockitoJUnitRunner.class)
class ServicioCategoriaTest {
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@InjectMocks
	private ServicioCategoria servicioCategoria;
	
	private List<Categoria> listaCategoriasPrueba;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		listaCategoriasPrueba = new ArrayList<>();
	}
	
	/**
	 * Prueba JUnit 1 - Metodo a Probar "recuperaCategorias".
	 * 
	 * Este caso revisa que al solicitar una lista de categorias registradas a un repositorio vacio
	 * se devolvera una lista vacia de productos
	 */
	
	@Test
	void testRecuperaCategorias() {
		//Caso 1 - Repositorio Vacio
		
		//Preparación de Datos
		when(categoriaRepository.findAll()).thenReturn(listaCategoriasPrueba);
		
		// Llamada al método bajo prueba
		List<Categoria> categoriasRegistradas = servicioCategoria.recuperaCategorias();
		
		//Verificación de datos del Repositorio
		assertEquals(0, categoriasRegistradas.size());
		
		//Caso 2 - Repositorio con elementos
		
		//Preparación de Datos
		Categoria categoriaA = new Categoria();
		Categoria categoriaB = new Categoria();
		listaCategoriasPrueba.add(categoriaA);
		listaCategoriasPrueba.add(categoriaB);
		
		// Llamada al método bajo prueba
		categoriasRegistradas = servicioCategoria.recuperaCategorias();
		
		//Verificación de datos del Repositorio
		assertEquals(2, categoriasRegistradas.size());
		
		//Verificación de llamadas al Repositorio
		verify(categoriaRepository, times(2)).findAll();
	}
	
	/**
	 * Prueba JUnit 2 - Metodo a Probar "registraCategoria".
	 * 
	 * Este caso revisa que al solicitar un nuevo registro al servicio categoria, este lo instanciara
	 * y lo agregara al repositorio
	 */
	
	@Test
	void testRegistraCategoria() {
		//Caso 1 - Registro en Repositorio Vacio
		
		//Preparación de Datos
		String categoriaA = "Categoría 1";
		
		when(categoriaRepository.findByNombre(anyString())).thenReturn(null);
		when(categoriaRepository.findAll()).thenReturn(listaCategoriasPrueba);
		
		// Llamada al método bajo prueba
		Categoria registroCategoriaA = servicioCategoria.registraCategoria(categoriaA);
		listaCategoriasPrueba.add(registroCategoriaA);
		
		//Verificación de datos del Repositorio
		assertEquals(registroCategoriaA.getNombre(), categoriaA);
		
		//Caso 2 - Registro en Repositorio con elementos
		String categoriaB = "Categoría 2";
		
		// Llamada al método bajo prueba
		Categoria registroCategoriaB = servicioCategoria.registraCategoria(categoriaB);
		listaCategoriasPrueba.add(registroCategoriaB);
		
		//Verificación de datos del Repositorio
		List<Categoria> categoriasRegistradas = servicioCategoria.recuperaCategorias();
		assertEquals(registroCategoriaB.getNombre(), categoriaB);
		assertEquals(2, categoriasRegistradas.size());
		
		//Caso 3 - Intento de registro de una Categoria Existente
		
		//Preparación de Datos
		when(categoriaRepository.findByNombre(categoriaA)).thenReturn(registroCategoriaA);
		
		// Llamada al método bajo prueba
		assertThrows(IllegalArgumentException.class, () -> {
			servicioCategoria.registraCategoria(categoriaA);
		});
	}
	
	/**
	 * Prueba JUnit 3 - Metodo a Probar "dameCategoria".
	 * 
	 * Este caso revisa que al solicitar al servicio categoria un registro de categoria
	 * este lo devolvera si es que está registrado.
	 */
	
	@Test
	void testDameCategoria() {
		//Caso 1 - Solicitud de Categoria en Repositorio Vacio
		
		// Llamada al método bajo prueba
		String categoriaA = "Categoría 1";
		Categoria registroCategoriaA = servicioCategoria.dameCategoria(categoriaA);
		
		assertEquals(registroCategoriaA, null);
		
		//Caso 2 - Solicitud de Categoria en Repositorio con elemento existente
		
		//Preparación de Datos
		Categoria categoria = new Categoria();
		categoria.setNombre(categoriaA);
		
		when(categoriaRepository.findByNombre(categoriaA)).thenReturn(categoria);
		
		// Llamada al método bajo prueba
		Categoria categoriaBuscada = servicioCategoria.dameCategoria(categoriaA);
		
		assertEquals(categoriaBuscada.getNombre(), categoriaA);
	}
}

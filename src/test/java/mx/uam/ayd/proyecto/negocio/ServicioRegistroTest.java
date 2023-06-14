package mx.uam.ayd.proyecto.negocio;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.Test;

import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.negocio.modelo.Registro;
import mx.uam.ayd.proyecto.datos.PuestoRepository;
import mx.uam.ayd.proyecto.datos.RegistroRepository;

@ExtendWith(MockitoExtension.class)
class ServicioRegistroTest {

	@Mock
	private RegistroRepository registroRepository;
	
	@InjectMocks
	private ServicioRegistro servicioRegistro;
	@Test
	void testRecuperarRegistros() {
		/*Ccaso de prueba:  No existen registros en la BD*/
		List<Registro> puestos=servicioRegistro.recuperarRegistros();
		assertEquals(0,puestos.size());
		/*Caso de prueba:  Si hay registross guardados me regresa la lista de registros en la BD*/
		
		ArrayList<Registro> lista=new ArrayList<>();
		Registro registro1=new Registro();
		registro1.setNombre("Vendedor");
		
		Registro registro2=new Registro();
		registro2.setNombre("Administrador");
		
		lista.add(registro1);
		lista.add(registro2);
		
		Iterable<Registro> listaIterable=lista;
		
		when(registroRepository.findAll()).thenReturn(listaIterable);
		puestos=servicioRegistro.recuperarRegistros();
		assertEquals(2,puestos.size()); 
	}

}

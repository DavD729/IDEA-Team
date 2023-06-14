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

import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.datos.PuestoRepository;

@ExtendWith(MockitoExtension.class)
class ServicioPuestoTeste {
	
	@Mock
	private PuestoRepository puestoRepository;
	
	@InjectMocks
	private ServicioPuesto servicioPuesto;
	
	@Test	
	void testRecuperaPuestos() {
		/*Ccaso de prueba:  No existen puestos en la BD*/
		List<Puesto> puestos=servicioPuesto.recuperaPuestos();
		assertEquals(0,puestos.size());
		/*Caso de prueba:  Si hay puestos guardados me regresa la lista de puestos en la BD*/
		
		ArrayList<Puesto> lista=new ArrayList<>();
		Puesto puesto1=new Puesto();
		puesto1.setNombre("Vendedor");
		
		Puesto puesto2=new Puesto();
		puesto2.setNombre("Administrador");
		
		lista.add(puesto1);
		lista.add(puesto2);
		
		Iterable<Puesto> listaIterable=lista;
		
		when(puestoRepository.findAll()).thenReturn(listaIterable);
		puestos=servicioPuesto.recuperaPuestos();
		assertEquals(2,puestos.size()); 
	}

}

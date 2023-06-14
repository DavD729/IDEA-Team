package mx.uam.ayd.proyecto.negocio;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.datos.PuestoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;

@ExtendWith(MockitoExtension.class)
class ServicioEmpleadoTest {
	@Mock //Genera un sustituto
	private EmpleadoRepository empleadoRepository;
	
	@Mock
	private PuestoRepository puestoRepository;
	
	@InjectMocks //inyecta  los sustitutos 
	private ServicioEmpleado servicioEmpleado;
	
	@Test
	void testagregarEmpleado() {
				
		/*Ccaso de Prueba 1: Que Agregue el empleado a la BD*/
		Puesto puesto1=new Puesto();
		when(puestoRepository.findByNombre("Vendedor")).thenReturn(puesto1);
		Empleado empleadoPrueba1=servicioEmpleado.agregarEmpleado("Alejandro", "Saucedo", "Ruiz", "Canteras 12", "5545520348", "uam@gmail.com", "Vendedor de Piso", "Vendedor");
		assertEquals("uam@gmail.com",empleadoPrueba1.getEmail()); 
	}
	
	@Test
	void testrecuperarEmpleado() {
		
		/*Caso de prueba 1: Que no recupere el empleado porque no este en la BD y sea null*/
		Empleado empleado=servicioEmpleado.recuperarEmpleado("uam@gmail.com");
		assertEquals(null,empleado); 
		
		/*Caso de prueba 2: Recupera un empleado por su email y empleado sea diferente de null*/
		 
			Empleado empleado2=new Empleado();
			
			empleado2.setNombre("Alejandro");
			empleado2.setApellidoP("Saucedo");
			empleado2.setApellidoM("Ruiz");
			empleado2.setDireccion("Canteras 14");
			empleado2.setTel("5545520348");
			empleado2.setEmail("uam@gmail.com");
			empleado2.setTarea("vendedor de piso");
			empleadoRepository.save(empleado2);
			
			when(empleadoRepository.findByEmail("uam@gmail.com")).thenReturn(empleado2);
			
			Empleado empleadoRecuperado=empleadoRepository.findByEmail("uam@gmail.com");
			assertEquals(empleado2,empleadoRecuperado); 
	}
	
	@Test
	void testrecuperarPuestoEmpleado(){
		
	}
	
	
	

}

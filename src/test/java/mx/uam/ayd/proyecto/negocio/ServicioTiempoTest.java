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
import mx.uam.ayd.proyecto.datos.RegistroRepository;
import mx.uam.ayd.proyecto.datos.TiempoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.negocio.modelo.Registro;
import mx.uam.ayd.proyecto.negocio.modelo.Tiempo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
class ServicioTiempoTest {

	@Mock //Genera un sustituto
	private EmpleadoRepository empleadoRepository;
	
	@Mock
	private PuestoRepository puestoRepository;
	
	@Mock
	private RegistroRepository	registroRepository;
	
	@Mock
	private TiempoRepository tiempoRepository;
	
	@InjectMocks //inyecta  los sustitutos 
	private ServicioTiempo servicioTiempo;
	
	@InjectMocks
	private ServicioEmpleado servicioEmpleado;
	
	
	@Test
	void testAgregarEmpleadoRegistroTiempo() {
		
		/*Ccaso de prueba: Guarde la informacion del tiempo en la Base de datos comprobado por el año registrado*/
		Puesto puesto1=new Puesto();
		Tiempo tiempoPrueba1=new Tiempo();
		when(puestoRepository.findByNombre("Vendedor")).thenReturn(puesto1);
		Empleado empleadoPrueba1=servicioEmpleado.agregarEmpleado("Alejandro", "Saucedo", "Ruiz", "Canteras 12", "5545520348", "uam@gmail.com", "Vendedor de Piso", "Vendedor");
		
		Registro registro1=new Registro();
		
		when(registroRepository.findByNombre("Entrando")).thenReturn(registro1);
		
		when(tiempoRepository.findByDia("14")).thenReturn(tiempoPrueba1);
		Tiempo tiempoP=servicioTiempo.agregarEmpleadoRegistroTiempo(empleadoPrueba1, "Entrando", "2023", "06", "14", "12", "25", "30");
		assertEquals("2023",tiempoP.getAnio()); 
		
	}
	

}

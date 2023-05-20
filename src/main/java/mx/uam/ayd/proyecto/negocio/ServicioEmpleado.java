package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.*;
import mx.uam.ayd.proyecto.negocio.modelo.*;

@Slf4j
@Service
public class ServicioEmpleado {
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private PuestoRepository puestoRepository;
	
	
	//tiene el servicio de Agregar al empleado con los datos ingresados
	public Empleado agregarEmpleado(String nombre, String apellidoP, String apellidoM,String direccion, String tel,  String email, String tarea,  String nombrePuesto) {
		Empleado empleado=empleadoRepository.findByEmail(email);
		if(empleado!=null) {
			throw new IllegalArgumentException("El Usuario con el email ingresado ingresado ya Existe");
		}
		
		Puesto puesto=puestoRepository.findByNombre(nombrePuesto);
		if(puesto==null) {
			throw new IllegalArgumentException("No se encontro el Puesto");	
		}
		
		log.info("Agregando empleado");
		empleado=new Empleado();
		empleado.setNombre(nombre);
		empleado.setApellidoP(apellidoP);
		empleado.setApellidoM(apellidoM);
		empleado.setDireccion(direccion);
		empleado.setTel(tel);
		empleado.setEmail(email);
		empleado.setTarea(tarea);
		
		empleadoRepository.save(empleado);
		puesto.addEmpleado(empleado);
		puestoRepository.save(puesto);

		return empleado;
	}

}

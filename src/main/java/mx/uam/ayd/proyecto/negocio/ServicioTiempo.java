package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.*;
import mx.uam.ayd.proyecto.negocio.modelo.*;

@Slf4j
@Service
public class ServicioTiempo {
	/*Instancias*/
	@Autowired
	private TiempoRepository tiempoRepository;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired 
	private ServicioRegistro servicioRegistro;
	
	@Autowired
	private RegistroRepository registroRepository;
	/*Constantes de los tipos de Registros*/
	private static final String ENTRADA="Entrando";
	private static final String SALIDA="Saliendo";
	
	/*Servicio para agregar al empleado al registro del tiempo de entrada y salida del checador*/
	public void agregarEmpleadoRegistroTiempo(Empleado empleado,String nombreRegistro,String anio,String mes,String dia,String hora,String min,String segundos) {
		/*Recupera el registro almacenado en la base de datos con el ingresado por el empleado*/		
		Registro registro=registroRepository.findByNombre(nombreRegistro);
		if(registro==null) {
			throw new IllegalArgumentException("No se encontro el registro");	
		}
	/*salva los datos del tiempo y registro en la base de datos en relacion al empleado en curso*/
		Tiempo tiempo=tiempoRepository.findByDia(dia);
		tiempo=new Tiempo();
		tiempo.setAnio(anio);
		tiempo.setMes(mes);
		tiempo.setDia(dia);
		tiempo.setHora(hora);
		tiempo.setMin(min);
		tiempo.setSegundos(segundos);
		tiempo.setIdEmpleado(empleado.getIdEmpleado());
		tiempoRepository.save(tiempo);
		if(nombreRegistro.equals(ENTRADA))
			empleado.setIdEntrada(tiempo.getIdTiempo());
		else if(nombreRegistro.equals(SALIDA))
			empleado.setIdSalida(tiempo.getIdTiempo());		
		empleadoRepository.save(empleado);
		/*Le agrega un registro al tiempo*/
		registro.addTiempo(tiempo);
		registroRepository.save(registro);		
	}
}

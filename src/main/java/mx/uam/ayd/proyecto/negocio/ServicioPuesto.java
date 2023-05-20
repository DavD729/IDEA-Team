package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PuestoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;

@Service

public class ServicioPuesto {

	@Autowired
	PuestoRepository puestoRepository;
	
	//Recupera los puestos que existen en la base de datos
	public List<Puesto> recuperaPuestos(){
		List <Puesto> puestos=new ArrayList<>();
		for(Puesto puesto:puestoRepository.findAll()) {
			puestos.add(puesto);
		}
		return puestos;
	}
}

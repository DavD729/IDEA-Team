package mx.uam.ayd.proyecto.negocio;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RegistroRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Registro;

@Service
public class ServicioRegistro {

	@Autowired
	RegistroRepository registroRepository;
	
	//Recuperar los registros existentes en la base de datos
	public List<Registro> recuperarRegistros(){
		List<Registro> registros=new ArrayList<>();
		for(Registro registro:registroRepository.findAll()) {
			registros.add(registro);
		}
		return registros;
	}
}

package mx.uam.ayd.proyecto.presentacion.agregarCategoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioCategoria;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.VentanaAgregarProducto;

@Slf4j
@Component
public class ControlAgregarCategoria {
	
	@Autowired
	private ServicioCategoria servicioCategoria;
	
	@Autowired
	private VentanaAgregarProducto ventanaProducto;
	
	public void inicio() {
		List<Categoria> categorias = servicioCategoria.recuperaCategorias();
		log.info("Categorias registradas: ");
		categorias.forEach((cat) -> {
			log.info(cat.getNombre());
		});
	}
	
	public boolean registraCategoria(String nombre) {
		try {
			servicioCategoria.registraCategoria(nombre);
		} catch(IllegalArgumentException e) {
			ventanaProducto.muestraErrorConMensaje("La categoria no puede ser creada, los argumentos no son Validos, " + e.getMessage());
			return false;
		}
		return true;
	}
	
	@Nullable
	public Categoria dameCategoria(String nombre) {
		return servicioCategoria.dameCategoria(nombre);
	}
}

package mx.uam.ayd.proyecto.presentacion.inventario.agregarCategoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioCategoria;
import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarProducto.VentanaAgregarProducto;

/**
 * Control usado para manipular el repositorio definido para "Categoria"
 * y sus metodos.
 * 
 * @author David
 *
 */
@Slf4j
@Component
public class ControlAgregarCategoria {
	
	@Autowired
	private ServicioCategoria servicioCategoria;
	
	@Autowired
	private VentanaAgregarProducto ventanaProducto;
	
	/**
	 *  Recupera los datos de la base de Datos de las categorias previamente registradas
	 */
	public void inicio() {
		List<Categoria> categorias = servicioCategoria.recuperaCategorias();
		log.info("Categorias registradas: ");
		categorias.forEach((cat) -> {
			log.info(cat.getNombre());
		});
	}
	
	/**
	 * Registra una nueva categoria a la base de datos usando su "Nombre"
	 * 
	 * @param nombre Nombre de la categoria a registrar
	 * 
	 * @return Si la categoria fue registrada o no
	 */
	public boolean registraCategoria(String nombre) {
		try {
			servicioCategoria.registraCategoria(nombre);
		} catch(IllegalArgumentException e) {
			ventanaProducto.muestraErrorConMensaje("La categoria no puede ser creada, los argumentos no son Validos");
			return false;
		}
		return true;
	}
	
	@Nullable
	public Categoria dameCategoria(String nombre) {
		return servicioCategoria.dameCategoria(nombre);
	}
}

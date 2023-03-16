package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Slf4j
@Service
public class ServicioProducto {
	
	@Autowired 
	ProductoRepository productoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Producto> recuperaProductos(){
		List<Producto> productos = new ArrayList<>();
		productoRepository.findAll().forEach((producto) -> {
			productos.add(producto);
		});
		
		return productos;
	}
	
	public Producto agregaProducto(String nombre, String categoria, int cantidad, double precio, String descripcion) {
		Producto producto = new Producto();
		log.info("Producto por agregar: " + nombre);
		
		if(categoriaRepository.findByNombre(categoria) == null) {
			throw new IllegalArgumentException("La categoria dada, no es váslida");
		}
		producto.setNombre(nombre);
		producto.setCategoria(categoria);
		producto.setEnExistencia(cantidad);
		producto.setPrecio(precio);
		producto.setDescripcion(descripcion);
		
		productoRepository.save(producto);
		return producto;
	}
}

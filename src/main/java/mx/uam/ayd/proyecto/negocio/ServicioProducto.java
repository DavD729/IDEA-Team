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
		if(productoRepository.findByNombre(nombre) != null) {
			throw new IllegalArgumentException("El producto ya se encuentra en Inventario");
		}
		
		if(categoriaRepository.findByNombre(categoria) == null) {
			throw new IllegalArgumentException("La categoria dada, no es válida");
		}
		log.info("Producto por agregar: " + nombre);
		
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setCategoria(categoria);
		producto.setEnExistencia(cantidad);
		producto.setPrecio(precio);
		producto.setDescripcion(descripcion);
		
		productoRepository.save(producto);
		return producto;
	}
	
	/**
	 * Ian - Integration
	 */
	
	private int numeroProductos;
	
	public Object[][] recuperaMatriz() {
		List <Producto> lista = new ArrayList<>();
		
		for(Producto pro:productoRepository.findAll()) {
			lista.add(pro);
		}
		
		this.setNumeroProductos(lista.size());
		
		Object[][] matriz = new Object[lista.size()][3];

		// Convertir lista de productos a una matriz
		for (int fila = 0; fila < lista.size(); fila++) {
			matriz[fila][0] = lista.get(fila).getNombre();
			matriz[fila][1] = lista.get(fila).getEnExistencia();
			matriz[fila][2] = lista.get(fila).getPrecio();
		}
		
		return matriz;
	}
	
	public void acualiza(List<Producto> lista) {
		productoRepository.deleteAll();
		productoRepository.saveAll(lista);	
	}
	
	public int getNumeroProductos() {
		return numeroProductos;
	}
	
	public void setNumeroProductos(int numeroProductos) {
		this.numeroProductos = numeroProductos;
	}
}

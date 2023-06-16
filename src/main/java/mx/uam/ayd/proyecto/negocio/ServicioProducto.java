package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.CategoriaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Servicio utilizado para manejar la información de la base de datos referente
 * a los productos.
 * 
 * @author David, Ian
 */

@Slf4j
@Service
public class ServicioProducto {
	
	@Autowired 
	ProductoRepository productoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	/**
	 * Devuelve una lista completa de los productos actuales registrados
	 * 
	 * @return Lista de Productos
	 */
	public List<Producto> recuperaProductos(){
		List<Producto> productos = new ArrayList<>();
		productoRepository.findAll().forEach((producto) -> {
			productos.add(producto);
		});
		
		return productos;
	}
	
	/**
	 * Devuelve los datos de los productos registrados actuales en forma de Matriz
	 * 
	 * @return Matriz de datos
	 */
	public Object[][] recuperaTablaDeProductos(){
		List<Producto> productos = new ArrayList<>();
		productoRepository.findAll().forEach((producto) -> {
			productos.add(producto);
		});
		
		Object[][] matrizDeDatos = new Object[productos.size()][5];
		for(int index = 0; index < productos.size(); index++) {
			matrizDeDatos[index][0] = productos.get(index).getIdProducto();
			matrizDeDatos[index][1] = productos.get(index).getNombre();
			matrizDeDatos[index][2] = productos.get(index).getEnExistencia();
			matrizDeDatos[index][3] = productos.get(index).getCategoria();
			matrizDeDatos[index][4] = productos.get(index).getPrecio();
		}
		
		return matrizDeDatos;
	}
	
	/**
	 * Proceso de registro de un producto nuevo hacia el inventario
	 * 
	 * @param nombre Nombre del producto a Registrar
	 * @param categoria Categoria en la que estará registrada
	 * @param cantidad Catidad de productos a ser registrados
	 * @param precio Valor del producto
	 * @param descripcion Información extra del producto
	 * @return Producto registrado
	 */
	
	public Producto agregaProducto(String nombre, String categoria, int cantidad, double precio, String descripcion) {
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
	 * Solicita al repositorio la busqueda de los productos que contengan el nombre solicitado
	 * 
	 * @param nombre Nombre de los productos que esten registrados
	 * @return Lista de todos los productos registrados con ese nombre (puede estar vacia)
	 */
	
	public List<Producto> buscaExistenciasDeProducto(String nombre) {
		return productoRepository.findByNombre(nombre);
	}
	
	public Optional<Producto> buscaProducto(long id) {
		return productoRepository.findById(id);
	}
	
	/**
	 * Ian - Integration
	 */
	
	/**
	 * Devuelve los datos de los productos registrados actuales en forma de Matriz para la ventana de abastecimiento
	 * 
	 * @return matriz 
	 */
	public Object[][] recuperaMatriz() {
		List <Producto> lista = new ArrayList<>();
		
		for(Producto pro:productoRepository.findAll()) {
			lista.add(pro);
		}
		
		Object[][] matriz = new Object[lista.size()][3];

		// Convertir lista de productos a una matriz
		for (int fila = 0; fila < lista.size(); fila++) {
			matriz[fila][0] = lista.get(fila).getNombre();
			matriz[fila][1] = lista.get(fila).getEnExistencia();
			matriz[fila][2] = lista.get(fila).getPrecio();
		}
		
		return matriz;
	}
	
	/*
	 * @param recibe la lista de prodcutos con los cambios hechos por el usuario desde la tabla 
	 */
	public void acualiza(List<Producto> lista) {
		productoRepository.saveAll(lista);	
	}
}

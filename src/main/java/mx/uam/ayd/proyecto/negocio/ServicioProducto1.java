package mx.uam.ayd.proyecto.negocio;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.datos.Producto1Repository;
import mx.uam.ayd.proyecto.negocio.modelo.Orden;
import mx.uam.ayd.proyecto.negocio.modelo.OrdenProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto1;

@SuppressWarnings("unused")
@Service
public class ServicioProducto1 {
	
	@Autowired 
	private Producto1Repository productoRepository;
	
	
	public ServicioProducto1() {
		// TODO Auto-generated constructor stub
	}


	public LinkedList<Producto1> getMenuOnline() {
		LinkedList<Producto1> menuOnline = new LinkedList<Producto1>();
		if(!productoRepository.findAll().isEmpty()) {
			for(Producto1 p:productoRepository.findAll()) {
				if(p.getOnlineEnabled()) {
					menuOnline.add(p);
				}
			}
		}else {
			Producto1 nulo = new Producto1();
			nulo.setNombre("No hay productos registrados");
			nulo.setComentario("no hay");
			
			menuOnline.add(nulo);
		}
		return menuOnline;
	}
	
	public List<Producto1> getProductos(){
		return productoRepository.findAll();
	}
	
	public Producto1 getProductoPorID(long idProducto){
		return productoRepository.findByidProducto(idProducto);
	}
	
	public List<OrdenProducto> recuperaListaOrdenProducto(long idProducto){
		return productoRepository.findByidProducto(idProducto).recuperaListaOrdenProducto();
	}
	
	public Producto1 recuperaProductoPorOrdenProducto(OrdenProducto ordenProducto){
		List<Producto1> producto = getProductos();
		Producto1 resultado = null;
		for (Producto1 producto1:producto) {
			List<OrdenProducto> ordenesProductos = recuperaListaOrdenProducto(producto1.getIdProducto());
			for (OrdenProducto orden:ordenesProductos) {
				if (orden.equals(ordenProducto)) resultado = producto1;
			}
		}
		return resultado;
	}
	/**
	 * Dado el id de un producto recupera la lista de ordenes en las que está ese producto.
	 * @param idProducto id del producto.
	 * @return lista de ordenes en las que está el producto.
	 */
	public List<Orden> recuperaListaOrdenList(long idProducto){
		return productoRepository.findByidProducto(idProducto).recuperaListaOrdenLista();
	}

	/**
	 * Encuentra en la base de datos y regresa el producto con nombre nombreProducto
	 * mediante el método productoRepository.findByNombre(nombreProducto).
	 * @param nombreProducto el nombre del producto buscado.
	 * @return El producto con nombre nombreProducto.
	 
	 */
	public Producto1 getProductoPorNombre(String nombreProducto){
		return productoRepository.findByNombre(nombreProducto).pop();
	}

	

	/**
	 * Dada una LinkedList<Object> con los atributos de un producto, lo agrega a la base de datos
	 * mediante el método productoRepository.save(p).
	 * @param dataCleanned una LinkedList<Object> con los atributos de un producto.
	 */
	public void agregarProducto(LinkedList<Object> dataCleanned){
		String nombre = dataCleanned.removeFirst().toString();
		float tipo = Float.parseFloat(dataCleanned.removeFirst().toString());
		float precio = Float.parseFloat(dataCleanned.removeFirst().toString());
		float ePrecio = Float.parseFloat(dataCleanned.removeFirst().toString());
		String comentario = dataCleanned.removeFirst().toString();
		String imagen = dataCleanned.removeFirst().toString();
		boolean onlineEnabled = Boolean.parseBoolean(dataCleanned.removeFirst().toString());

		Producto1 aux = new Producto1(nombre, tipo, precio, ePrecio, imagen, onlineEnabled, comentario);
		productoRepository.save(aux);
	}

	/**
	 * Dado un pproducto, lo agrega o actualiza según sea el caso.
	 * @param p el producto a agregar o actualizar.
	
	 */
	public void agregarProducto(Producto1 p){
		productoRepository.save(p);
	}


}


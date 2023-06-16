package mx.uam.ayd.proyecto.negocio;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.OrdenOnlineRepository;
import mx.uam.ayd.proyecto.datos.OrdenProductoRepository;
import mx.uam.ayd.proyecto.datos.Producto1Repository;
import mx.uam.ayd.proyecto.negocio.modelo.OrdenOnline;
import mx.uam.ayd.proyecto.negocio.modelo.OrdenProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto1;

@Service
public class ServicioOrdenProducto {

	@Autowired 
	private OrdenProductoRepository ordenProductoRepository;
	
	@Autowired 
	private OrdenOnlineRepository ordenOnlineRepository;

	@Autowired 
	private Producto1Repository producto1Repository;
	
	public void agregaOrdenProducto(int cantidad, float subtotal, long idProducto, long idOrdenOnline) {
		
		Producto1 producto = producto1Repository.findByidProducto(idProducto);
		OrdenOnline ordenOnline = ordenOnlineRepository.findByidOrdenOnline(idOrdenOnline);

		if(producto == null) {
			throw new IllegalArgumentException("No se encontró el Producto con id "+idProducto);
		}
		if(ordenOnline == null) {
			throw new IllegalArgumentException("No se encontró la OrdenOnline con id "+idOrdenOnline);
		}
	
		OrdenProducto o = new OrdenProducto();
		o.setCantidad(cantidad);
		o.setSubtotal(subtotal);
		
		ordenProductoRepository.save(o);
		
		producto.addOrdenProducto(o);
		ordenOnline.addOrdenProducto(o);

		producto1Repository.save(producto);
		ordenOnlineRepository.save(ordenOnline);
	}
	
	public LinkedList<OrdenProducto> recuperaOrdenesProducto(){
		return ordenProductoRepository.findAll();
	}
	
	public OrdenProducto recuperaOrdenProductoPorID(long idOrdenProducto) {
		return ordenProductoRepository.findByidOrdenProducto(idOrdenProducto);
	}

}

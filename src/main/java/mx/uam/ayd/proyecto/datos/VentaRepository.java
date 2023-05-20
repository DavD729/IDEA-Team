package mx.uam.ayd.proyecto.datos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Repository
public interface VentaRepository extends CrudRepository <Venta, Long>{
	/*
	 * Busca todas las ventas asociadas a una fecha
	 */
	public List<Venta> findByDate(LocalDate fecha);
	public List<Venta> findByTipoVenta(int tipoVenta);
}
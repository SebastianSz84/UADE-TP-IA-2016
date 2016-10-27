package dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Venta;

@Local
public interface VentaDAO extends BaseDAO {
	public Venta get(int idVenta);

	public List<Venta> listVentas();
}
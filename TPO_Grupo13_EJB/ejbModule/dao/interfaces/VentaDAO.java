package dao.interfaces;

import javax.ejb.Local;

import entities.Venta;

@Local
public interface VentaDAO extends BaseDAO {
	public Venta get(int idVenta);
}
package dao;

import javax.ejb.Stateless;

import dao.interfaces.VentaDAO;
import entities.Venta;

@Stateless
public class VentaDAOBean extends BaseDAOBean implements VentaDAO {

	public VentaDAOBean() {

	}

	public Venta get(int idVenta) {
		return getEntity(Venta.class, idVenta);
	}

}

package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dao.interfaces.VentaDAO;
import entities.Venta;

@Stateless
@LocalBean
public class VentaDAOBean extends BaseDAO implements VentaDAO {

	public VentaDAOBean() {

	}

	public Venta get(int idVenta) {
		return getEntity(Venta.class, idVenta);
	}

}

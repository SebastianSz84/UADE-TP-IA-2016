package dao;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	public List<Venta> listVentas(int idUsuario) {
		return getEntityManager().createQuery("FROM Venta V WHERE V.usuario.id =:idUsuario")
				.setParameter("idUsuario", idUsuario).getResultList();
	}

}

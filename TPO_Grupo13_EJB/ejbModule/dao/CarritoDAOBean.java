package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dao.interfaces.CarritoDAO;
import entities.Carrito;

@Stateless
public class CarritoDAOBean extends BaseDAOBean implements CarritoDAO {

	public Carrito get(int idUsuario) {
		Query query = getEntityManager().createQuery("FROM Carrito C WHERE C.idUsuario = :idUsuario");
		query.setParameter("idUsuario", idUsuario);

		@SuppressWarnings("unchecked")
		List<Carrito> results = query.getResultList();
		if (results.size() > 0)
			return results.get(0);
		return null;

	}
}

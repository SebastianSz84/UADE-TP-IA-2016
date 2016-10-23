package dao;

import javax.ejb.Stateless;

import dao.interfaces.CarritoDAO;
import entities.Carrito;

@Stateless
public class CarritoDAOBean extends BaseDAOBean implements CarritoDAO {

	public Carrito get(int idUsuario) {
		return (Carrito) getEntityManager().createQuery("FROM Carrito C WHERE C.idUsuario = :idUsuario")
				.setParameter("idUsuario", idUsuario).getSingleResult();
	}
}

package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dao.interfaces.ProductoDAO;
import entities.Producto;

/**
 * Session Bean implementation class ProductoDAOBean
 */
@Stateless
public class ProductoDAOBean extends BaseDAOBean implements ProductoDAO {

	public ProductoDAOBean() {
	}

	@SuppressWarnings("unchecked")
	public Producto get(int codigo, String idDeposito) {

		Query query = getEntityManager()
				.createQuery("FROM Producto P WHERE p.codigo =:codigo AND p.idDeposito =:idDeposito")
				.setParameter("codigo", codigo).setParameter("idDeposito", idDeposito);

		List<Producto> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}

		return (Producto) list.get(0);
	}

	public List<Producto> listProductos() {
		return getAll(Producto.class, "Producto");
	}
}

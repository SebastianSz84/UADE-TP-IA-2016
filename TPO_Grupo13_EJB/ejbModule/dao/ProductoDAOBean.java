package dao;

import java.util.List;

import javax.ejb.Stateless;

import dao.interfaces.ProductoDAO;
import entities.Producto;

/**
 * Session Bean implementation class ProductoDAOBean
 */
@Stateless
public class ProductoDAOBean extends BaseDAOBean implements ProductoDAO {

	public ProductoDAOBean() {
	}

	public Producto get(int codigo) {
		return getEntity(Producto.class, codigo);
	}

	public List<Producto> listProductos() {
		return getAll(Producto.class, "Producto");
	}
}

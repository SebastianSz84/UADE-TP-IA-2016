package dao;

import java.util.List;

import dao.interfaces.ProductoDAO;
import entities.Producto;

public class ProductoDAOBean extends BaseDAO implements ProductoDAO {

	public Producto get(int codigo) {
		return getEntity(Producto.class, codigo);
	}

	public List<Producto> listProductos() {
		return getAll(Producto.class, "Producto");
	}

}

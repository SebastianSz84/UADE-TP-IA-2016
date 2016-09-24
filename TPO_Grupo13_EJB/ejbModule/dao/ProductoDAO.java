package Dao;

import java.util.List;

import Entities.Producto;

public class ProductoDAO extends BaseDAO {
	
	public static Producto get(int codigo) {
		return getEntity(Producto.class, codigo);
	}
	
	public static List<Producto> listProductos()
	{
		return getAll(Producto.class, "Producto");
	}

}

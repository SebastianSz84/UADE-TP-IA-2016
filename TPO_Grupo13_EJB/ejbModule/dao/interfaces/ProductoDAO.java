package dao.interfaces;

import java.util.List;

import entities.Producto;

public interface ProductoDAO {

	public Producto get(int codigo);

	public List<Producto> listProductos();
}
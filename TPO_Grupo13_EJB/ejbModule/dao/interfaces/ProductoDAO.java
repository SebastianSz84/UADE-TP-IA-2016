package dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Producto;

@Local
public interface ProductoDAO {
	public Producto get(int codigo);

	public List<Producto> listProductos();

}
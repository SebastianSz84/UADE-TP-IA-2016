package dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Producto;

@Local
public interface ProductoDAO extends BaseDAO {
	public Producto get(int codigo, String idDeposito);

	public List<Producto> listProductos();

}
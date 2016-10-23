package dao.interfaces;

import javax.ejb.Local;

import entities.Carrito;

@Local
public interface CarritoDAO extends BaseDAO {

	public Carrito get(int idUsuario);
}

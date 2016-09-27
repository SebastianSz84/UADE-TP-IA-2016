package dao.interfaces;

import javax.ejb.Local;

import entities.Categoria;

@Local
public interface CategoriaDAO extends BaseDAO {
	public Categoria get(int idCategoria);

	public Categoria get(String nombre);

}
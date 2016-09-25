package dao;

import dao.interfaces.CategoriaDAO;
import entities.Categoria;

public class CategoriaDAOBean extends BaseDAO implements CategoriaDAO {

	public CategoriaDAOBean() {

	}

	public Categoria get(int idCategoria) {
		return getEntityManager().find(Categoria.class, idCategoria);
	}

	public Categoria get(String nombre) {
		return (Categoria) getEntityManager().createQuery("FROM Categoria C WHERE C.nombre = :nombre")
				.setParameter("nombre", nombre);
	}
}

package dao;

import javax.ejb.Stateless;

import dao.interfaces.CategoriaDAO;
import entities.Categoria;

/**
 * Session Bean implementation class CategoriaDAOBean
 */
@Stateless
public class CategoriaDAOBean extends BaseDAO implements CategoriaDAO {

	/**
	 * Default constructor.
	 */
	public CategoriaDAOBean() {
		// TODO Auto-generated constructor stub
	}

	public Categoria get(int idCategoria) {
		return getEntityManager().find(Categoria.class, idCategoria);
	}

	public Categoria get(String nombre) {
		return (Categoria) getEntityManager().createQuery("FROM Categoria C WHERE C.nombre = :nombre")
				.setParameter("nombre", nombre);
	}
}

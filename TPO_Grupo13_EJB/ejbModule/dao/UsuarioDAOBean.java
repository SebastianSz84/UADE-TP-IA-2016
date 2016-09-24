package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.interfaces.UsuarioDAO;
import entities.Usuario;

/**
 * Session Bean implementation class UsuarioDAOBean
 */
@Stateless
@LocalBean
public class UsuarioDAOBean implements UsuarioDAO {

	/**
	 * Default constructor.
	 */
	public UsuarioDAOBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "unit1")
	private EntityManager em;

	public Usuario get(int idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}
}

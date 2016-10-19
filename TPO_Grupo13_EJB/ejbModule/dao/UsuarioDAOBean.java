package dao;

import javax.ejb.Stateless;

import dao.interfaces.UsuarioDAO;
import entities.Usuario;

@Stateless
public class UsuarioDAOBean extends BaseDAOBean implements UsuarioDAO {

	public UsuarioDAOBean() {
	}

	public Usuario get(String userName) {
		return (Usuario) getEntityManager().createQuery("FROM Usuario C WHERE C.userName =:userName")
				.setParameter("userName", userName).getSingleResult();
	}
}

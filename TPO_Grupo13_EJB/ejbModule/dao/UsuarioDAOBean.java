package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dao.interfaces.UsuarioDAO;
import entities.Usuario;

@Stateless
@LocalBean
public class UsuarioDAOBean extends BaseDAO implements UsuarioDAO {

	public UsuarioDAOBean() {
	}

	public Usuario get(String userName) {
		return (Usuario) getEntityManager().createQuery("FROM Usuario C WHERE C.userName =:userName")
				.setParameter("userName", userName).getResultList();
	}
}

package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dao.interfaces.UsuarioDAO;
import entities.Usuario;

@Stateless
public class UsuarioDAOBean extends BaseDAOBean implements UsuarioDAO {

	public UsuarioDAOBean() {
	}

	@SuppressWarnings("unchecked")
	public Usuario get(String userName) {
		Query query = getEntityManager().createQuery("FROM Usuario C WHERE C.userName =:userName")
				.setParameter("userName", userName);

		List<Usuario> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}

		return (Usuario) list.get(0);
	}

	public Usuario get(int idUsuario) {
		return getEntityManager().find(Usuario.class, idUsuario);
	}
}

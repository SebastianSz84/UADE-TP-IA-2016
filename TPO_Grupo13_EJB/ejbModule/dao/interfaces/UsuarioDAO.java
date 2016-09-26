package dao.interfaces;

import javax.ejb.Local;

import entities.Usuario;

@Local
public interface UsuarioDAO extends BaseDAO {
	public Usuario get(String userName);
}
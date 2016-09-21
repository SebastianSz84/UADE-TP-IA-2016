package Dao;

import Entities.Categoria;
import Entities.Usuario;

public class UsuarioDAO extends BaseDAO {
	
	public static Usuario get(int idusuario)
	{
		return getEntity(Usuario.class, idusuario);
	}
	
	public static Usuario get(String userName)
	{
		return (Usuario) getSession().createQuery("FROM Usuario C WHERE C.userName =:userName").setParameter("userName", userName).uniqueResult();
	}

}

package Dao;

import Entities.Categoria;

public class CategoriaDAO extends BaseDAO {
	
	public static Categoria getCategoria(int idCategoria)
	{
		return getEntity(Categoria.class, 1);
	}

	public static Categoria getCategoria(String nombre)
	{
		return (Categoria) getSession().createQuery("FROM Categoria C WHERE C.nombre = :nombre").setParameter("nombre", nombre).uniqueResult();
	}
}

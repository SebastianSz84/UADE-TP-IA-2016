package dao.interfaces;

import entities.Categoria;

public interface CategoriaDAO {
	public Categoria get(int idCategoria);

	public Categoria get(String nombre);
}
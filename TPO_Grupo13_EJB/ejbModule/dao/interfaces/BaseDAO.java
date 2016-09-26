package dao.interfaces;

import java.util.List;

public interface BaseDAO {
	public <T> T getEntity(Class<T> cls, int id);

	public <T> T getEntity(Class<T> cls, String id);

	public <T> T saveEntity(T entity);

	public <T> void deleteEntity(T entity);

	public <T> List<T> getAll(Class<T> cls, String tabla);

	public boolean deleteAll(String tabla);

}
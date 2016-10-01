package dao.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

public interface BaseDAO {
	public EntityManager getEntityManager();

	public <T> T getEntity(Class<T> cls, int id);

	public <T> T getEntity(Class<T> cls, String id);

	public <T> T saveEntity(T entity);

	public <T> void deleteEntity(T entity);

	public <T> List<T> getAll(Class<T> cls, String tabla);

	public void deleteAll(String tabla);

}
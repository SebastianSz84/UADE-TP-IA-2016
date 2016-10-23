package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.interfaces.BaseDAO;

public class BaseDAOBean implements BaseDAO {

	@PersistenceContext(unitName = "unit1")
	private EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public <T> T getEntity(Class<T> cls, int id) {
		try {
			T entity = em.find(cls, id);
			return entity;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public <T> T getEntity(Class<T> cls, String id) {
		try {
			T entity = em.find(cls, id);
			return entity;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public <T> T saveEntity(T entity) {
		em.persist(entity);
		return entity;
	}

	public <T> T updateEntity(T entity) {
		em.merge(entity);
		return entity;
	}

	public <T> void deleteEntity(T entity) {
		em.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> cls, String tabla) {
		try {
			List<T> list = em.createQuery("from " + tabla).getResultList();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void deleteAll(String tabla) {
		String hql = String.format("delete from %s", tabla);
		em.createQuery(hql).executeUpdate();
	}
}
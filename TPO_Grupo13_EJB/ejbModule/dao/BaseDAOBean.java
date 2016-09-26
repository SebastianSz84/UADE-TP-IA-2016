package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		}
		return entity;
	}

	public <T> void deleteEntity(T entity) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.remove(entity);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		}
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

	public boolean deleteAll(String tabla) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		String hql = String.format("delete from %s", tabla);
		try {
			em.createQuery(hql).executeUpdate();
			tx.commit();
			return true;
		} catch (Exception ex) {
			tx.rollback();
		}
		return false;
	}
}
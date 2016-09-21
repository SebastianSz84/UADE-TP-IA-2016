package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Entities.*;

public class BaseDAO
{
	private static SessionFactory _factory;
	private static Session _session;
	
	public static Session getSession()
	{
		if (_session == null)
		{
			// Create session
			Configuration cfg = new Configuration();
			cfg.addAnnotatedClass(Producto.class);
			cfg.addAnnotatedClass(Categoria.class);
			cfg.addAnnotatedClass(Usuario.class);
			cfg.addAnnotatedClass(Venta.class);
			cfg.addAnnotatedClass(ItemVenta.class);
			cfg.addAnnotatedClass(Carrito.class);
			cfg.addAnnotatedClass(ItemCarrito.class);
			
			cfg.configure("hibernate.cfg.xml");
			_factory = cfg.buildSessionFactory();
			_session = _factory.openSession();
		}
		return _session;
	}
	
	public static Session getNewSession()
	{
		return _factory.openSession();
	}
	
	public static <T> T getEntity(Class<T> cls, int id)
	{
		try
		{
			T entity = getSession().get(cls, id);
			return entity;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public static <T> T getEntity(Class<T> cls, String id)
	{
		try
		{
			T entity = getSession().get(cls, id);
			return entity;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public static <T> T saveEntity(T entity)
	{
		Transaction tx = getSession().beginTransaction();
		try
		{
			getSession().saveOrUpdate(entity);
			tx.commit();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			tx.rollback();
		}
		return entity;
	}
	
	public static <T> void deleteEntity(T entity)
	{
		Transaction tx = getSession().beginTransaction();
		try
		{
			getSession().delete(entity);
			tx.commit();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			tx.rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAll(Class<T> cls, String tabla)
	{
		try
		{
			List<T> list = getSession().createQuery("from " + tabla).list();
			return list;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public static boolean deleteAll(String tabla)
	{
		Transaction tx = getSession().beginTransaction();
		String hql = String.format("delete from %s", tabla);
		try
		{
			getSession().createQuery(hql).executeUpdate();
			tx.commit();
			return true;
		}
		catch (Exception ex)
		{
			tx.rollback();
		}
		return false;
	}
}
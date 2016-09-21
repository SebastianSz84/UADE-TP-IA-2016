package Dao;

import Entities.Venta;

public class VentaDAO extends BaseDAO {
	
	public static Venta get(int idVenta)
	{
		return getEntity(Venta.class, idVenta);
	}

}

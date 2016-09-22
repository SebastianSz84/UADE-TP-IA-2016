package Controllers;

import java.util.ArrayList;
import java.util.List;

import Dao.ProductoDAO;
import Dto.ProductoDTO;
import Entities.Producto;

public class PortalWeb {
	private static PortalWeb instancia;

	public static PortalWeb getInstancia() {
		if (instancia == null) {
			instancia = new PortalWeb();
		}
		return instancia;
	}

	public static void setInstancia(PortalWeb instancia) {
		PortalWeb.instancia = instancia;
	}

	public List<ProductoDTO> listadoProductos() {
		List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
		for (Producto p : ProductoDAO.listProductos()) {
			lista.add(p.getDTO());
		}
		return lista;
	}
}
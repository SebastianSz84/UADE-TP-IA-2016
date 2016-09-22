package Tests;

import java.util.List;

import Controllers.PortalWeb;
import Dto.ProductoDTO;

public class SebaTest {

	public static void main(String[] args) {
		List<ProductoDTO> lista = PortalWeb.getInstancia().listadoProductos();
		for (ProductoDTO p : lista) {
			System.out.println(p.getNombre() + p.getPrecio());
		}
	}

}
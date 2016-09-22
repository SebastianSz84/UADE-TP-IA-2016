package Controllers;

import java.util.ArrayList;
import java.util.List;

import Dao.ProductoDAO;
import Dao.UsuarioDAO;
import Dto.ProductoDTO;
import Dto.ResultadoOperacion;
import Entities.Producto;
import Entities.Usuario;

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

	public ResultadoOperacion altaUsuario(String nombre, String apellido, String userName, String password) {
		if (nombre.trim().isEmpty())
			return new ResultadoOperacion(false, "El nombre no puede estar vacio");

		if (apellido.trim().isEmpty())
			return new ResultadoOperacion(false, "El apellido no puede estar vacio");

		if (userName.trim().isEmpty())
			return new ResultadoOperacion(false, "El username no puede estar vacio");

		if (password.trim().isEmpty())
			return new ResultadoOperacion(false, "El password no puede estar vacio");

		try {

			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setUserName(userName);
			usuario.setPassword(password);

			UsuarioDAO.saveEntity(usuario);

			return new ResultadoOperacion(true, "Usuario creado con exito");
		} catch (Exception ex) {
			return new ResultadoOperacion(false, "Error al crear usuario : " + ex.getMessage());
		}
	}
}
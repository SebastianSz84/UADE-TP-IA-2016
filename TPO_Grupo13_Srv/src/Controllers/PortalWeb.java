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

	private List<Usuario> usuarios;

	private PortalWeb() {
		usuarios = new ArrayList<Usuario>();
	}

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

	public ResultadoOperacion loginUsuario(String userName, String password) {
		if (userName.trim().isEmpty())
			return new ResultadoOperacion(false, "El username no puede estar vacio");

		if (password.trim().isEmpty())
			return new ResultadoOperacion(false, "El password no puede estar vacio");

		try {
			Usuario usuario = this.buscarUsuario(userName);

			if (usuario == null)
				return new ResultadoOperacion(false, "El usuario no existe");

			if (usuario.tenesPassword(password)) {
				return new ResultadoOperacion(true, "Exito");
			} else {
				return new ResultadoOperacion(false, "La clave es incorrecta");
			}

		} catch (Exception ex) {
			return new ResultadoOperacion(false, "Error en el login de usuario : " + ex.getMessage());
		}
	}

	private Usuario buscarUsuario(String userName) {

		for (Usuario u : usuarios) {
			if (u.sosUsuario(userName))
				return u;
		}

		Usuario usuario = UsuarioDAO.get(userName);

		if (usuario != null)
			usuarios.add(usuario);

		return usuario;
	}

}
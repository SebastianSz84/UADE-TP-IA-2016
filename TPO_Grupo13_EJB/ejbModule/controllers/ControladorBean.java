package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import controllers.interfaces.Controlador;
import controllers.interfaces.RankingDTO;
import dao.interfaces.CategoriaDAO;
import dao.interfaces.ProductoDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VentaDAO;
import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.UsuarioDTO;
import dto.VentaDTO;
import entities.Producto;
import entities.Usuario;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;

@Stateless
public class ControladorBean implements Controlador {

	@EJB
	private UsuarioDAO usuarioDAOBean;
	@EJB
	private ProductoDAO productoDAOBean;
	@EJB
	private CategoriaDAO categoriaDAOBean;
	@EJB
	private VentaDAO ventaDAOBean;

	private List<Usuario> usuarios;

	public ControladorBean() {
		usuarios = new ArrayList<Usuario>();
	}

	public ResultadoOperacionListadoProductosDTO listadoProductos() {
		try {

			List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
			for (Producto p : productoDAOBean.listProductos()) {
				lista.add(p.getDTO());
			}
			return new ResultadoOperacionListadoProductosDTO(true, "Exito", lista);
		} catch (Exception ex) {
			return new ResultadoOperacionListadoProductosDTO(false, "Error al listar productos" + ex.getMessage(),
					null);
		}

	}

	public ResultadoOperacionDTO altaUsuario(String nombre, String apellido, String userName, String password) {
		if (nombre.trim().isEmpty())
			return new ResultadoOperacionDTO(false, "El nombre no puede estar vacio");

		if (apellido.trim().isEmpty())
			return new ResultadoOperacionDTO(false, "El apellido no puede estar vacio");

		if (userName.trim().isEmpty())
			return new ResultadoOperacionDTO(false, "El username no puede estar vacio");

		if (password.trim().isEmpty())
			return new ResultadoOperacionDTO(false, "El password no puede estar vacio");

		try {

			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setUserName(userName);
			usuario.setPassword(password);

			usuarioDAOBean.saveEntity(usuario);

			return new ResultadoOperacionDTO(true, "Usuario creado con exito");
		} catch (Exception ex) {
			return new ResultadoOperacionDTO(false, "Error al crear usuario : " + ex.getMessage());
		}
	}

	public ResultadoOperacionDTO loginUsuario(String userName, String password) {
		if (userName.trim().isEmpty())
			return new ResultadoOperacionDTO(false, "El username no puede estar vacio");

		if (password.trim().isEmpty())
			return new ResultadoOperacionDTO(false, "El password no puede estar vacio");

		try {
			Usuario usuario = this.buscarUsuario(userName);

			if (usuario == null)
				return new ResultadoOperacionDTO(false, "El usuario no existe");

			if (usuario.tenesPassword(password)) {
				return new ResultadoOperacionDTO(true, "Exito");
			} else {
				return new ResultadoOperacionDTO(false, "La clave es incorrecta");
			}

		} catch (Exception ex) {
			return new ResultadoOperacionDTO(false, "Error en el login de usuario : " + ex.getMessage());
		}
	}

	public List<ProductoDTO> listadoBestSellers() {
		return null;
	}

	public CarritoDTO crearCarrito(UsuarioDTO u) {
		return null;
	}

	public void modificarCarrito(CarritoDTO c) {
	}

	public VentaDTO confirmarCarrito(CarritoDTO c) {
		return null;
	}

	private Usuario buscarUsuario(String userName) {

		for (Usuario u : usuarios) {
			if (u.sosUsuario(userName))
				return u;
		}

		Usuario usuario = usuarioDAOBean.get(userName);

		if (usuario != null)
			usuarios.add(usuario);

		return usuario;
	}

	@Override
	public ResultadoOperacionDTO actualizarBestSellers(List<RankingDTO> lista) {
		rankingDAOBean.deleteAll();
		for (RankingDTO r : lista) {
			rankingDAOBean.saveEntity(r);
		}
		return null;
	}

}
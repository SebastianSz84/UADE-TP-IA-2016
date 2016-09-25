package controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import controllers.interfaces.IControlador;
import dao.ProductoDAOBean;
import dao.UsuarioDAOBean;
import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.ResultadoOperacion;
import dto.UsuarioDTO;
import dto.VentaDTO;
import entities.Producto;
import entities.Usuario;

public class Controlador implements IControlador {

	@EJB
	private UsuarioDAOBean usuarioDAOBean;
	@EJB
	private ProductoDAOBean productoDAOBean;

	private List<Usuario> usuarios;

	public Controlador() {
		usuarios = new ArrayList<Usuario>();
	}

	public List<ProductoDTO> listadoProductos() {
		List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
		for (Producto p : productoDAOBean.listProductos()) {
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

			usuarioDAOBean.saveEntity(usuario);

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

	public List<ProductoDTO> listadoBestSellers() throws RemoteException {
		return null;
	}

	public CarritoDTO crearCarrito(UsuarioDTO u) throws RemoteException {
		return null;
	}

	public void modificarCarrito(CarritoDTO c) throws RemoteException {
	}

	public VentaDTO confirmarCarrito(CarritoDTO c) throws RemoteException {
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

}
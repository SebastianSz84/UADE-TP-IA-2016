package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import controllers.interfaces.Controlador;
import dao.interfaces.CarritoDAO;
import dao.interfaces.CategoriaDAO;
import dao.interfaces.ProductoDAO;
import dao.interfaces.RankingDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VentaDAO;
import dto.CarritoDTO;
import dto.ItemCarritoDTO;
import dto.ProductoDTO;
import dto.RankingDTO;
import dto.VentaDTO;
import entities.Carrito;
import entities.Categoria;
import entities.ItemCarrito;
import entities.ItemVenta;
import entities.Producto;
import entities.Ranking;
import entities.Usuario;
import entities.Venta;
import integracion.interfaces.AdminNotificaciones;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoRankingDTO;
import resultadoOperacionDTOs.ResultadoOperacionUsuarioDTO;
import resultadoOperacionDTOs.ResultadoOperacionVentaDTO;

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
	@EJB
	private RankingDAO rankingDAOBean;
	@EJB
	private AdminNotificaciones admNotif;
	@EJB
	private CarritoDAO carritoDAOBean;

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

	public ResultadoOperacionUsuarioDTO loginUsuario(String userName, String password) {
		if (userName.trim().isEmpty())
			return new ResultadoOperacionUsuarioDTO(false, "El username no puede estar vacio", null);

		if (password.trim().isEmpty())
			return new ResultadoOperacionUsuarioDTO(false, "El password no puede estar vacio", null);

		try {
			Usuario usuario = this.buscarUsuario(userName);

			if (usuario == null)
				return new ResultadoOperacionUsuarioDTO(false, "El usuario no existe", null);

			if (usuario.tenesPassword(password)) {
				return new ResultadoOperacionUsuarioDTO(true, "Exito", usuario.getDTO());
			} else {
				return new ResultadoOperacionUsuarioDTO(false, "La clave es incorrecta", null);
			}

		} catch (Exception ex) {
			return new ResultadoOperacionUsuarioDTO(false, "Error en el login de usuario : " + ex.getMessage(), null);
		}
	}

	@Transactional
	public ResultadoOperacionDTO actualizarBestSellers(List<RankingDTO> lista) {

		try {
			rankingDAOBean.deleteAll("Ranking");
			for (RankingDTO r : lista) {

				Ranking ranking = new Ranking();

				Producto p = productoDAOBean.get(r.getProducto().getCodigo());

				ranking.setProducto(p);
				ranking.setCodigoProducto(p.getCodigo());
				ranking.setPosicion(r.getPosicion());

				rankingDAOBean.saveEntity(ranking);
			}
			return new ResultadoOperacionDTO(true, "BestSellers acutalizados con exito");
		} catch (Exception ex) {
			return new ResultadoOperacionDTO(false, "Error al actualizar BestSellers : " + ex.getMessage());
		}
	}

	public ResultadoOperacionDTO nuevoProducto(ProductoDTO prodDTO) {

		try {
			Producto p = new Producto();
			Categoria c = categoriaDAOBean.get(prodDTO.getCategoria().getId());

			if (c == null) {
				c = new Categoria();
				c.setNombre(prodDTO.getCategoria().getNombre());
				categoriaDAOBean.saveEntity(c);
			}

			p.setCategoria(c);
			p.setCodigo(prodDTO.getCodigo());
			p.setDatosExtra(prodDTO.getDatosExtra());
			p.setDescripcion(prodDTO.getDescripcion());
			p.setMarca(prodDTO.getMarca());
			p.setNombre(prodDTO.getNombre());
			p.setOrigen(prodDTO.getOrigen());
			p.setPrecio(prodDTO.getPrecio());
			p.setUrlImagen(prodDTO.getUrlImagen());
			productoDAOBean.saveEntity(p);

			return new ResultadoOperacionDTO(true, "Nuevo producto creado con exito");
		} catch (Exception ex) {
			return new ResultadoOperacionDTO(false, "Error al crear producto : " + ex.getMessage());
		}
	}

	public ResultadoOperacionListadoRankingDTO listadoBestSellers() {
		try {
			List<RankingDTO> lista = new ArrayList<RankingDTO>();
			for (Ranking r : rankingDAOBean.listRanking()) {
				lista.add(r.getDTO());
			}
			return new ResultadoOperacionListadoRankingDTO(true, "Exito", lista);
		} catch (Exception ex) {
			return new ResultadoOperacionListadoRankingDTO(false, "Error al listar productos" + ex.getMessage(), null);
		}
	}

	public ResultadoOperacionDTO crearCarrito(CarritoDTO carritoDTO) {
		try {
			Carrito c;
			Boolean isNew = false;
			List<ItemCarrito> items = new ArrayList<ItemCarrito>();
			Usuario u = usuarioDAOBean.get(carritoDTO.getIdUsuario());
			c = carritoDAOBean.get(u.getId());
			if (c == null) {
				c = new Carrito();
				isNew = true;
			}
			c.setIdUsuario(u.getId());
			c.setUsuario(u);

			for (ItemCarritoDTO ic : carritoDTO.getItems()) {
				Producto p = new Producto();
				Categoria cate = new Categoria();
				cate.setId(ic.getProducto().getCategoria().getId());
				cate.setNombre(ic.getProducto().getCategoria().getNombre());
				p.setCategoria(cate);
				p.setCodigo(ic.getProducto().getCodigo());
				p.setDatosExtra(ic.getProducto().getDatosExtra());
				p.setDescripcion(ic.getProducto().getDescripcion());
				p.setMarca(ic.getProducto().getMarca());
				p.setNombre(ic.getProducto().getNombre());
				p.setOrigen(ic.getProducto().getOrigen());
				p.setPrecio(ic.getProducto().getPrecio());
				p.setUrlImagen(ic.getProducto().getUrlImagen());
				items.add(new ItemCarrito(ic.getCantidad(), p));
			}
			c.setItems(items);
			if (isNew)
				carritoDAOBean.saveEntity(c);
			else
				carritoDAOBean.updateEntity(c);

			return new ResultadoOperacionDTO(true, "Carrito guardado");
		} catch (Exception ex) {
			return new ResultadoOperacionDTO(false, "Error al crear producto : " + ex.getMessage());
		}
	}

	public void modificarCarrito(CarritoDTO c) {
		admNotif.enviarNotificacion("Carrito modificado: " + c.toString());
	}

	public ResultadoOperacionVentaDTO confirmarCarrito(CarritoDTO c) {
		try {
			Venta v = new Venta();
			List<ItemVenta> items = new ArrayList<ItemVenta>();
			Usuario u = usuarioDAOBean.get(c.getIdUsuario());
			v.setIdUsuario(u.getId());
			// v.setUsuario(u);
			for (ItemCarritoDTO ic : c.getItems()) {
				Producto p = new Producto();
				Categoria cate = new Categoria();
				cate.setId(ic.getProducto().getCategoria().getId());
				cate.setNombre(ic.getProducto().getCategoria().getNombre());
				p.setCategoria(cate);
				p.setCodigo(ic.getProducto().getCodigo());
				p.setDatosExtra(ic.getProducto().getDatosExtra());
				p.setDescripcion(ic.getProducto().getDescripcion());
				p.setMarca(ic.getProducto().getMarca());
				p.setNombre(ic.getProducto().getNombre());
				p.setOrigen(ic.getProducto().getOrigen());
				p.setPrecio(ic.getProducto().getPrecio());
				p.setUrlImagen(ic.getProducto().getUrlImagen());
				items.add(new ItemVenta(ic.getCantidad(), p));
			}
			v.setItems(items);
			ventaDAOBean.saveEntity(v);
			VentaDTO venDTO = v.getDTO();
			admNotif.enviarInfoVenta(venDTO);
			return new ResultadoOperacionVentaDTO(true, "Se registro una venta", venDTO);
		} catch (Exception ex) {
			return new ResultadoOperacionVentaDTO(false, "Error al crear producto : " + ex.getMessage(), null);
		}
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
	public ResultadoOperacionDTO actualizarEstadoVenta(int numeroVenta, String estado) {
		try {
			Venta v = ventaDAOBean.get(numeroVenta);
			if (v == null) {
				return new ResultadoOperacionDTO(false, "Venta " + Integer.toString(numeroVenta) + " no encontrada.");
			}
			v.setEstado(estado);
			ventaDAOBean.saveEntity(v);
			return new ResultadoOperacionDTO(true, "Venta " + Integer.toString(numeroVenta) + " actualizada.");
		} catch (Exception e) {
			return new ResultadoOperacionDTO(false, e.getMessage());
		}
	}

	// PARA TESTS!!!!!!!!!!!!!!!

	@Override
	public ResultadoOperacionDTO testNotificacionLogMon() {
		return admNotif.enviarNotificacion("Operacion dummy");
	}

}
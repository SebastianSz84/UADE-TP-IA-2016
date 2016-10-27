package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import controllers.interfaces.Controlador;
import dao.interfaces.CarritoDAO;
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
import entities.ItemCarrito;
import entities.ItemVenta;
import entities.Producto;
import entities.Ranking;
import entities.Usuario;
import entities.Venta;
import integracion.interfaces.AdminNotificaciones;
import resultadoOperacionDTOs.ResultadoOperacionCarritoDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoVentasDTO;
import resultadoOperacionDTOs.ResultadoOperacionUsuarioDTO;
import resultadoOperacionDTOs.ResultadoOperacionVentaDTO;

@Stateless
public class ControladorBean implements Controlador {

	@EJB
	private UsuarioDAO usuarioDAOBean;
	@EJB
	private ProductoDAO productoDAOBean;
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

			p.setCodigo(prodDTO.getCodigo());
			p.setDatosExtra(prodDTO.getDatosExtra());
			p.setDescripcion(prodDTO.getDescripcion());
			p.setMarca(prodDTO.getMarca());
			p.setNombre(prodDTO.getNombre());
			p.setOrigen(prodDTO.getOrigen());
			p.setPrecio(prodDTO.getPrecio());
			p.setFoto(prodDTO.getFoto());
			p.setTipo(prodDTO.getTipo());
			p.setIdDeposito(prodDTO.getIdDeposito());
			productoDAOBean.saveEntity(p);

			return new ResultadoOperacionDTO(true, "Nuevo producto creado con exito");
		} catch (Exception ex) {
			return new ResultadoOperacionDTO(false, "Error al crear producto : " + ex.getMessage());
		}
	}

	public ResultadoOperacionDTO crearCarrito(CarritoDTO carritoDTO) {
		try {
			Carrito carrito = carritoDAOBean.get(carritoDTO.getIdUsuario());
			Boolean isNew = false;
			if (carrito == null) {
				carrito = new Carrito();
				carrito.setIdUsuario(carritoDTO.getIdUsuario());
				isNew = true;
			} else {
				carritoDAOBean.borrarListaItems(carrito);
			}

			loadCarrito(carrito, carritoDTO);
			if (isNew)
				carritoDAOBean.saveEntity(carrito);
			else
				carritoDAOBean.updateEntity(carrito);

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
			Venta v = loadVenta(c);
			ventaDAOBean.saveEntity(v);
			Carrito carrito = new Carrito();
			loadCarrito(carrito, c);
			carritoDAOBean.borrarListaItems(carrito);
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

	private void loadCarrito(Carrito carrito, CarritoDTO carritoDTO) {

		for (ItemCarritoDTO itemDto : carritoDTO.getItems()) {

			Producto p = new Producto();

			p.setCodigo(itemDto.getProducto().getCodigo());
			p.setDatosExtra(itemDto.getProducto().getDatosExtra());
			p.setDescripcion(itemDto.getProducto().getDescripcion());
			p.setMarca(itemDto.getProducto().getMarca());
			p.setNombre(itemDto.getProducto().getNombre());
			p.setOrigen(itemDto.getProducto().getOrigen());
			p.setPrecio(itemDto.getProducto().getPrecio());
			p.setFoto(itemDto.getProducto().getFoto());
			p.setTipo(itemDto.getProducto().getTipo());

			carrito.getItems().add(new ItemCarrito(itemDto.getCantidad(), p));

		}
	}

	private Venta loadVenta(CarritoDTO c) {
		Venta v = new Venta();
		v.setFecha(new Date());
		List<ItemVenta> items = new ArrayList<ItemVenta>();
		Usuario u = usuarioDAOBean.get(c.getIdUsuario());
		v.setIdUsuario(u.getId());
		for (ItemCarritoDTO ic : c.getItems()) {
			Producto p = new Producto();

			p.setCodigo(ic.getProducto().getCodigo());
			p.setDatosExtra(ic.getProducto().getDatosExtra());
			p.setDescripcion(ic.getProducto().getDescripcion());
			p.setMarca(ic.getProducto().getMarca());
			p.setNombre(ic.getProducto().getNombre());
			p.setOrigen(ic.getProducto().getOrigen());
			p.setPrecio(ic.getProducto().getPrecio());
			p.setFoto(ic.getProducto().getFoto());
			p.setTipo(ic.getProducto().getTipo());
			items.add(new ItemVenta(ic.getCantidad(), p));
		}
		v.setItems(items);
		return v;
	}

	@Override
	public ResultadoOperacionCarritoDTO getCarrito(int carritoId) {
		try {
			CarritoDTO carritoDto = carritoDAOBean.get(carritoId).getDTO();
			return new ResultadoOperacionCarritoDTO(true, "Exito", carritoDto);
		} catch (Exception ex) {
			return new ResultadoOperacionCarritoDTO(false, "Error al traer el carrito. " + ex.getMessage(), null);
		}
	}

	public ResultadoOperacionListadoVentasDTO listadoVentas() {
		try {
			List<VentaDTO> lista = new ArrayList<VentaDTO>();
			for (Venta v : ventaDAOBean.listVentas()) {
				lista.add(v.getDTO());
			}
			return new ResultadoOperacionListadoVentasDTO(true, "Exito", lista);
		} catch (Exception ex) {
			return new ResultadoOperacionListadoVentasDTO(false, "Error al listar ventas" + ex.getMessage(), null);
		}

	}
}
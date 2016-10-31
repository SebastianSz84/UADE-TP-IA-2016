package controllers;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;

import controllers.interfaces.Controlador;
import dto.CarritoDTO;
import dto.RankingDTO;
import resultadoOperacionDTOs.ResultadoOperacionCarritoDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoVentasDTO;
import resultadoOperacionDTOs.ResultadoOperacionUsuarioDTO;
import resultadoOperacionDTOs.ResultadoOperacionVentaDTO;

public class BusinessDelegate {

	private static BusinessDelegate instancia;
	private Controlador controlador;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private BusinessDelegate() {
		try {
			final Hashtable props = new Hashtable();
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new javax.naming.InitialContext(props);
			controlador = (Controlador) context.lookup("ejb:" + "TPO_Grupo13_EAR" + "/" + "TPO_Grupo13_EJB" + "/" + ""
					+ "/" + "ControladorBean" + "!" + Controlador.class.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static BusinessDelegate getInstancia() {
		if (instancia == null) {
			instancia = new BusinessDelegate();
		}
		return instancia;
	}

	public ResultadoOperacionDTO altaUsuario(String nombre, String apellido, String userName, String password) {
		return controlador.altaUsuario(nombre, apellido, userName, password);
	}

	public ResultadoOperacionListadoProductosDTO listadoProductos() {
		return controlador.listadoProductos();
	}

	public ResultadoOperacionDTO actualizarBestSellers(List<RankingDTO> lista) {
		return controlador.actualizarBestSellers(lista);
	}

	public ResultadoOperacionDTO actualizarEstadoVenta(int numeroVenta, String estado) {
		return controlador.actualizarEstadoVenta(numeroVenta, estado);
	}

	public ResultadoOperacionDTO testNotificacionLogMon() {
		return controlador.testNotificacionLogMon();
	}

	public ResultadoOperacionUsuarioDTO login(String userName, String password) {
		return controlador.loginUsuario(userName, password);
	}

	public ResultadoOperacionDTO saveCarrito(CarritoDTO carrito, String accion) {
		return controlador.crearCarrito(carrito, accion);
	}

	public ResultadoOperacionVentaDTO confirmCarrito(CarritoDTO carrito) {
		return controlador.confirmarCarrito(carrito);
	}

	public ResultadoOperacionCarritoDTO getCarrito(int carritoId) {
		return controlador.getCarrito(carritoId);
	}

	public ResultadoOperacionListadoVentasDTO listadoVentas(int idUsuario) {
		return controlador.listadoVentas(idUsuario);
	}
}
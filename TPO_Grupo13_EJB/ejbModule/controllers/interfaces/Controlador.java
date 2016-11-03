package controllers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.RankingDTO;
import resultadoOperacionDTOs.ResultadoOperacionCarritoDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoVentasDTO;
import resultadoOperacionDTOs.ResultadoOperacionUsuarioDTO;
import resultadoOperacionDTOs.ResultadoOperacionVentaDTO;

@Remote
public interface Controlador {
	public ResultadoOperacionListadoProductosDTO listadoProductos();

	public ResultadoOperacionDTO altaUsuario(String nombre, String apellido, String userName, String password);

	public ResultadoOperacionUsuarioDTO loginUsuario(String userName, String password);

	public ResultadoOperacionDTO crearCarrito(CarritoDTO u, String accion);

	public ResultadoOperacionVentaDTO confirmarCarrito(CarritoDTO c);

	public ResultadoOperacionDTO actualizarBestSellers(List<RankingDTO> lista);

	public ResultadoOperacionDTO nuevoProducto(ProductoDTO prodDTO);

	public ResultadoOperacionDTO actualizarEstadoVenta(int numeroVenta, String estado);

	public ResultadoOperacionCarritoDTO getCarrito(int carritoId);

	public ResultadoOperacionListadoVentasDTO listadoVentas(int idUsuario);
}
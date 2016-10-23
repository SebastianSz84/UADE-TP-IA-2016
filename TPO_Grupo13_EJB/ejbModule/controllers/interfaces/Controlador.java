package controllers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.RankingDTO;
import dto.VentaDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoRankingDTO;
import resultadoOperacionDTOs.ResultadoOperacionUsuarioDTO;

@Remote
public interface Controlador {
	public ResultadoOperacionListadoProductosDTO listadoProductos();

	public ResultadoOperacionListadoRankingDTO listadoBestSellers();

	public ResultadoOperacionDTO altaUsuario(String nombre, String apellido, String userName, String password);

	public ResultadoOperacionUsuarioDTO loginUsuario(String userName, String password);

	public ResultadoOperacionDTO crearCarrito(CarritoDTO u);

	public void modificarCarrito(CarritoDTO c);

	public VentaDTO confirmarCarrito(CarritoDTO c);

	public ResultadoOperacionDTO actualizarBestSellers(List<RankingDTO> lista);

	public ResultadoOperacionDTO nuevoProducto(ProductoDTO prodDTO);

	public ResultadoOperacionDTO actualizarEstadoVenta(int numeroVenta, String estado);

	public ResultadoOperacionDTO testNotificacionLogMon();
}
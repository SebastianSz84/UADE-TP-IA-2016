package controllers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.RankingDTO;
import dto.UsuarioDTO;
import dto.VentaDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;

@Remote
public interface Controlador {
	public ResultadoOperacionListadoProductosDTO listadoProductos();

	public List<ProductoDTO> listadoBestSellers();

	public ResultadoOperacionDTO altaUsuario(String nombre, String apellido, String userName, String password);

	public ResultadoOperacionDTO loginUsuario(String userName, String password);

	public CarritoDTO crearCarrito(UsuarioDTO u);

	public void modificarCarrito(CarritoDTO c);

	public VentaDTO confirmarCarrito(CarritoDTO c);

	public ResultadoOperacionDTO actualizarBestSellers(List<RankingDTO> lista);

}
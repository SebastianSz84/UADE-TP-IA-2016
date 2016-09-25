package controllers.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.UsuarioDTO;
import dto.VentaDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;

public interface IControlador {
	public ResultadoOperacionListadoProductosDTO listadoProductos() throws RemoteException;

	public List<ProductoDTO> listadoBestSellers() throws RemoteException;

	public ResultadoOperacionDTO altaUsuario(String nombre, String apellido, String userName, String password)
			throws RemoteException;

	public ResultadoOperacionDTO loginUsuario(String userName, String password) throws RemoteException;

	public CarritoDTO crearCarrito(UsuarioDTO u) throws RemoteException;

	public void modificarCarrito(CarritoDTO c) throws RemoteException;

	public VentaDTO confirmarCarrito(CarritoDTO c) throws RemoteException;
}
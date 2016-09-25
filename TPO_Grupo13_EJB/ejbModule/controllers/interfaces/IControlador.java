package controllers.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.ResultadoOperacion;
import dto.UsuarioDTO;
import dto.VentaDTO;

public interface IControlador {
	public List<ProductoDTO> listadoProductos() throws RemoteException;

	public List<ProductoDTO> listadoBestSellers() throws RemoteException;

	public ResultadoOperacion altaUsuario(String nombre, String apellido, String userName, String password)
			throws RemoteException;

	public ResultadoOperacion loginUsuario(String userName, String password) throws RemoteException;

	public CarritoDTO crearCarrito(UsuarioDTO u) throws RemoteException;

	public void modificarCarrito(CarritoDTO c) throws RemoteException;

	public VentaDTO confirmarCarrito(CarritoDTO c) throws RemoteException;
}
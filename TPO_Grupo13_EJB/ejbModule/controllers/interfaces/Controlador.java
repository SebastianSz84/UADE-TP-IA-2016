package controllers.interfaces;

import java.rmi.RemoteException;
import java.util.List;

import dto.CarritoDTO;
import dto.ProductoDTO;
import dto.UsuarioDTO;
import dto.VentaDTO;

public interface Controlador {
	public List<ProductoDTO> listadoProductos() throws RemoteException;

	public List<ProductoDTO> listadoBestSellers() throws RemoteException;

	public void loginUsuario(String user, String pass) throws RemoteException;

	public CarritoDTO crearCarrito(UsuarioDTO u) throws RemoteException;

	public void modificarCarrito(CarritoDTO c) throws RemoteException;

	public VentaDTO confirmarCarrito(CarritoDTO c) throws RemoteException;
}
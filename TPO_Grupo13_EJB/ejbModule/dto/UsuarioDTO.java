package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import integracion.dto.ClienteLMDTO;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private String apellido;
	private String userName;
	private String password;
	private int dni;
	private List<VentaDTO> ventas;
	private CarritoDTO carrito;
	private String coordenadasX;
	private String coordenadasY;

	public UsuarioDTO() {
		super();
		ventas = new ArrayList<VentaDTO>();
	}

	public UsuarioDTO(String nombre, String apellido, String userName, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.userName = userName;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDNI() {
		return dni;
	}

	public void setDNI(int dni) {
		this.dni = dni;
	}

	public List<VentaDTO> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentaDTO> ventas) {
		this.ventas = ventas;
	}

	public void addVenta(VentaDTO venta) {
		this.ventas.add(venta);
	}

	public boolean sosUsuario(String userName) {
		return this.userName.equals(userName);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CarritoDTO getCarrito() {
		return carrito;
	}

	public void setCarrito(CarritoDTO carrito) {
		this.carrito = carrito;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCoordenadasX() {
		return coordenadasX;
	}

	public void setCoordenadasX(String coordenadasX) {
		this.coordenadasX = coordenadasX;
	}

	public String getCoordenadasY() {
		return coordenadasY;
	}

	public void setCoordenadasY(String coordenadasY) {
		this.coordenadasY = coordenadasY;
	}

	public ClienteLMDTO convertirLMDTO() {
		ClienteLMDTO c = new ClienteLMDTO();
		c.setApellido(apellido);
		c.setDNI(dni);
		c.setNombre(nombre);
		return c;
	}

}
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dto.UsuarioDTO;
import dto.VentaDTO;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column(unique = true)
	private String userName;
	@Column
	private String password;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private List<Venta> ventas;
	@Column
	private String coordenadasX;
	@Column
	private String coordenadasY;
	@Column(unique = true)
	private int dni;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Carrito carrito;

	public Usuario() {
		super();
		ventas = new ArrayList<Venta>();
	}

	public Usuario(String nombre, String apellido, String userName, String password, String coordenadasX,
			String coordenadasY, int dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.userName = userName;
		this.password = password;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.dni = dni;
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

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public void addVenta(Venta venta) {
		this.ventas.add(venta);
	}

	public boolean sosUsuario(String userName) {
		return this.userName.equals(userName);
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public void addItemACarrito(Producto producto, int cantidad) {
		if (this.carrito == null)
			this.carrito = new Carrito();

		this.carrito.setUsuario(this);

		this.carrito.addItem(producto, cantidad);
	}

	public void vaciarCarrito() {
		this.carrito = new Carrito();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioDTO getDTO() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setApellido(apellido);
		dto.setId(id);
		dto.setNombre(nombre);
		dto.setPassword(password);
		dto.setUserName(userName);
		List<VentaDTO> lista = new ArrayList<VentaDTO>();
		for (Venta v : ventas) {
			lista.add(v.getDTO());
		}
		dto.setVentas(lista);
		dto.setCoordenadasX(coordenadasX);
		dto.setCoordenadasY(coordenadasY);
		dto.setDNI(dni);
		return dto;
	}

	public boolean tenesPassword(String password) {
		return this.password.equals(password);
	}

	public Integer getDNI() {
		return dni;
	}

	public void setDNI(Integer dni) {
		this.dni = dni;
	}

}
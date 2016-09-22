package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import Dto.CarritoDTO;
import Dto.ItemCarritoDTO;

@Entity
@Table(name = "Carrito")
public class Carrito implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idUsuario", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "usuario"))
	private Integer idUsuario;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	private List<ItemCarrito> items;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Usuario usuario;

	public Carrito() {
		super();
		this.items = new ArrayList<ItemCarrito>();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemCarrito> getItems() {
		return items;
	}

	public void setItems(List<ItemCarrito> items) {
		this.items = items;
	}

	public void addItem(Producto producto, int cantidad) {
		ItemCarrito item = new ItemCarrito();
		item.setProducto(producto);
		item.setCantidad(cantidad);
		this.items.add(item);

	}

	public CarritoDTO getDTO() {
		CarritoDTO dto = new CarritoDTO();
		dto.setId(idUsuario);
		List<ItemCarritoDTO> lista = new ArrayList<ItemCarritoDTO>();
		for (ItemCarrito ic : items) {
			lista.add(ic.getDTO());
		}
		dto.setItems(lista);
		return dto;
	}
}
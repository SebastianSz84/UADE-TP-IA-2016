package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dto.ItemVentaDTO;

@Entity
@Table(name = "ItemVenta")
public class ItemVenta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private int cantidad;
	@Column
	private double precio;
	@ManyToOne
	@JoinColumn(name = "IdProducto")
	private Producto producto;

	public ItemVenta() {
		super();
	}

	public ItemVenta(int cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.setPrecio();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.setPrecio();
	}

	public double getPrecio() {
		return precio;
	}

	private void setPrecio() {
		this.precio = this.cantidad * this.producto.getPrecio();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
		this.setPrecio();
	}

	public String toString() {
		return this.getProducto().getNombre() + " x " + this.getCantidad();
	}

	public ItemVentaDTO getDTO() {
		ItemVentaDTO dto = new ItemVentaDTO();
		dto.setCantidad(cantidad);
		dto.setId(id);
		dto.setPrecio(precio);
		dto.setProducto(producto.getDTO());
		return dto;
	}
}

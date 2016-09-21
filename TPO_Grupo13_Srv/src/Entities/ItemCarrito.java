package Entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="ItemCarrito")
public class ItemCarrito implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private int cantidad;
	@ManyToOne
	@JoinColumn(name="codigoProducto")
	private Producto producto;
	
	public ItemCarrito() {
		super();
	}
	
	public ItemCarrito(int cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;		
		this.producto = producto;
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
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String toString() {
		return this.getProducto().getNombre() + " x " + this.getCantidad();
	}
}

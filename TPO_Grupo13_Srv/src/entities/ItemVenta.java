package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="ItemVenta")
public class ItemVenta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private int cantidad;
	@Column
	private String estadoEntrega;
	@ManyToOne
	@JoinColumn(name="codigoProducto")
	private Producto producto;
	
	public ItemVenta() {
		super();
	}
	
	public ItemVenta(int cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;		
		this.producto = producto;
		this.setEstadoEntrega("Pendiente"); // Lo inicializo así para nuestra DB, pero el estado lo mantiene Despacho
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

	public String getEstadoEntrega() {
		return estadoEntrega;
	}

	public void setEstadoEntrega(String estadoEntrega) {
		this.estadoEntrega = estadoEntrega;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String toString() {
		return this.getProducto().getNombre() + " x " + this.getCantidad() + " [" +
				this.getEstadoEntrega() + "]";
	}
}

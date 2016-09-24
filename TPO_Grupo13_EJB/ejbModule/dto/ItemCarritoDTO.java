package dto;

import java.io.Serializable;

public class ItemCarritoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int cantidad;
	private ProductoDTO producto;

	public ItemCarritoDTO() {
		super();
	}

	public ItemCarritoDTO(int cantidad, ProductoDTO producto) {
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

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public String toString() {
		return this.getProducto().getNombre() + " x " + this.getCantidad();
	}
}
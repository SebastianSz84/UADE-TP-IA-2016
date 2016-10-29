package dto;

import java.io.Serializable;

import integracion.dto.ItemVentaLMDTO;

public class ItemVentaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int cantidad;
	private double precio;
	private ProductoDTO producto;

	public ItemVentaDTO() {
		super();
	}

	public ItemVentaDTO(int cantidad, ProductoDTO producto) {
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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

	public ItemVentaLMDTO convertirLMDTO() {
		ItemVentaLMDTO itLM = new ItemVentaLMDTO();

		itLM.setCantidad(cantidad);
		itLM.setDescripcion(producto.getDescripcion());
		itLM.setIdProducto(producto.getCodigo());
		itLM.setMarca(producto.getMarca());
		itLM.setPrecio(producto.getPrecio());

		return itLM;
	}
}
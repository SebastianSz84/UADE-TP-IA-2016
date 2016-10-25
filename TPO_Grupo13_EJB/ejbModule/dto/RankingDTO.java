package dto;

import java.io.Serializable;

public class RankingDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProductoDTO producto;
	private Integer posicion;

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

}
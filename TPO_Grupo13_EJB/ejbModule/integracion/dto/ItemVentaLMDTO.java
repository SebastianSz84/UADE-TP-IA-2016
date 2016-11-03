package integracion.dto;

import java.io.Serializable;

public class ItemVentaLMDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArticuloLMDTO articulo;
	private Integer cantidad;

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArticuloLMDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloLMDTO articulo) {
		this.articulo = articulo;
	}

}
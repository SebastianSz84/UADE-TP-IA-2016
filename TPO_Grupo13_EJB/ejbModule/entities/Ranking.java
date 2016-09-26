package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import dto.RankingDTO;

public class Ranking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idProducto", unique = true, nullable = false)
	private Integer idProducto;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Producto producto;

	private Integer posicion;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public RankingDTO getDTO() {
		RankingDTO dto = new RankingDTO();
		dto.setProducto(producto.getDTO());
		dto.setPosicion(posicion);
		return dto;
	}

}

package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class VentaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String estado;
	private List<ItemVentaDTO> items;
	private Date fecha; // Es un getdate default en la base de datos

	public VentaDTO() {
		super();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ItemVentaDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemVentaDTO> items) {
		this.items = items;
	}
}
package Dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Carrito")
public class CarritoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String estado;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemCarritoDTO> items;
	@Column
	private Date fecha;
	
	public CarritoDTO() {
		super();
		this.setEstado("Pendiente"); //Lo inicializo en pendiente.
		this.fecha = new Date(Calendar.getInstance().getTimeInMillis());
		this.items = new ArrayList<ItemCarritoDTO>();
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

	public List<ItemCarritoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemCarritoDTO> items) {
		this.items = items;
	}
	
}

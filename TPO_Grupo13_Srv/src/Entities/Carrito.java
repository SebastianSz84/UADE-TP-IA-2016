package Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Dto.CarritoDTO;
import Dto.ItemCarritoDTO;

@Entity
@Table(name = "Carrito")
public class Carrito implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String estado;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemCarrito> items;
	@Column
	private Date fecha;

	public Carrito() {
		super();
		this.setEstado("Pendiente"); // Lo inicializo en pendiente.
		this.fecha = new Date(Calendar.getInstance().getTimeInMillis());
		this.items = new ArrayList<ItemCarrito>();
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

	public List<ItemCarrito> getItems() {
		return items;
	}

	public void setItems(List<ItemCarrito> items) {
		this.items = items;
	}

	public CarritoDTO getDTO() {
		CarritoDTO dto = new CarritoDTO();
		dto.setEstado(estado);
		dto.setFecha(fecha);
		dto.setId(id);
		List<ItemCarritoDTO> lista = new ArrayList<ItemCarritoDTO>();
		for (ItemCarrito ic : items) {
			lista.add(ic.getDTO());
		}
		dto.setItems(lista);
		return dto;
	}
}
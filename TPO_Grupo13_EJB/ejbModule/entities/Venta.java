package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dto.ItemVentaDTO;
import dto.VentaDTO;

@Entity
@Table(name = "Venta")
public class Venta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column
	private String estado;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "idVenta")
	private List<ItemVenta> items;
	@Column
	private Date fecha; // Es un getdate default en la base de datos

	@SuppressWarnings("deprecation")
	public Venta() {
		super();
		this.setEstado("Pendiente"); // Lo inicializo en pendiente.
		this.fecha = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		this.items = new ArrayList<ItemVenta>();
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

	public List<ItemVenta> getItems() {
		return items;
	}

	public void setItems(List<ItemVenta> items) {
		this.items = items;
	}

	public VentaDTO getDTO() {
		VentaDTO dto = new VentaDTO();
		dto.setEstado(estado);
		dto.setFecha(fecha);
		dto.setId(id);
		List<ItemVentaDTO> lista = new ArrayList<ItemVentaDTO>();
		for (ItemVenta iv : items) {
			lista.add(iv.getDTO());
		}
		dto.setItems(lista);
		// dto.setUsuario(usuario.getDTO());
		return dto;
	}

}

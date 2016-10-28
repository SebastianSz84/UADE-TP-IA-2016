package integracion.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VentaLMDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private ClienteLMDTO cliente;
	private String latitud;
	private String longitud;
	private Date fechaHoraVenta; // Es un getdate default en la base de datos
	private double monto;
	private String nombrePortal = "Portal Web Grupo 13";
	private List<ItemVentaLMDTO> itemsVenta;

	public VentaLMDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteLMDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteLMDTO cliente) {
		this.cliente = cliente;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Date getFechaHoraVenta() {
		return fechaHoraVenta;
	}

	public void setFechaHoraVenta(Date fechaHoraVenta) {
		this.fechaHoraVenta = fechaHoraVenta;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getNombrePortal() {
		return nombrePortal;
	}

	public void setNombrePortal(String nombrePortal) {
		this.nombrePortal = nombrePortal;
	}

	public List<ItemVentaLMDTO> getItemsVenta() {
		return itemsVenta;
	}

	public void setItemsVenta(List<ItemVentaLMDTO> itemsVenta) {
		this.itemsVenta = itemsVenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
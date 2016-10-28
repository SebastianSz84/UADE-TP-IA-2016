package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import integracion.dto.ItemVentaLMDTO;
import integracion.dto.VentaLMDTO;

public class VentaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String estado;
	private List<ItemVentaDTO> items;
	private Date fecha; // Es un getdate default en la base de datos
	private UsuarioDTO usuario;

	public VentaDTO() {
		super();
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
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

	public VentaLMDTO convertirLMDTO() {
		VentaLMDTO v = new VentaLMDTO();

		v.setFechaHoraVenta(fecha);
		v.setId(id);
		// TODO
		v.setLatitud("QUE VA ACA???");
		v.setLongitud("Y ACA???");

		double monto = 0;
		List<ItemVentaLMDTO> lista = new ArrayList<>();

		for (ItemVentaDTO it : items) {
			lista.add(it.convertirLMDTO());
			monto = monto + it.getPrecio() * it.getCantidad();
		}

		v.setMonto(monto);
		v.setCliente(usuario.convertirLMDTO());

		return v;
	}
}
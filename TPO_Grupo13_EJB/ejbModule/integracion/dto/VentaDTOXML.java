package integracion.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import dto.ItemVentaDTO;
import dto.VentaDTO;

@XmlRootElement(name = "venta")
@XmlAccessorType(XmlAccessType.FIELD)
public class VentaDTOXML implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private Integer id;
	@XmlElement
	private String estado;
	@XmlElementWrapper(name = "itemsVenta")
	@XmlElement(name = "item")
	private List<ItemVentaDTOXML> items;
	@XmlElement
	private Date fecha;

	public VentaDTOXML() {
		super();
		this.items = new ArrayList<ItemVentaDTOXML>();
	}

	public VentaDTOXML(VentaDTO venta) {
		this.setEstado(venta.getEstado());
		this.setFecha(venta.getFecha());
		this.setId(venta.getId());
		this.items = new ArrayList<ItemVentaDTOXML>();
		for (ItemVentaDTO itDTO : venta.getItems()) {
			ItemVentaDTOXML itXML = new ItemVentaDTOXML();
			itXML.setCantidad(itDTO.getCantidad());
			itXML.setCodigoProducto(itDTO.getProducto().getCodigo());
			itXML.setPrecio(itDTO.getPrecio());
			this.items.add(itXML);
		}
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

	public List<ItemVentaDTOXML> getItems() {
		return items;
	}

	public void setItems(List<ItemVentaDTOXML> items) {
		this.items = items;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
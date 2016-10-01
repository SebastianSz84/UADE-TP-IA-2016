package integracion.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dto.ProductoDTO;

@XmlRootElement(name = "producto")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProdXMLDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int codigo;
	@XmlElement
	private String nombre;
	@XmlElement
	private String descripcion;
	@XmlElement
	private String marca;
	@XmlElement
	private double precio;
	@XmlElement
	private String urlImagen;
	@XmlElement
	private String origen;
	@XmlElement
	private String datosExtra;
	@XmlElement
	private String categoria;

	public ProdXMLDTO() {

	}

	public ProdXMLDTO(ProductoDTO prodDTO) {
		this.setCategoria(prodDTO.getCategoria().getNombre());
		this.setCodigo(prodDTO.getCodigo());
		this.setDatosExtra(prodDTO.getDatosExtra());
		this.setDescripcion(prodDTO.getDescripcion());
		this.setMarca(prodDTO.getMarca());
		this.setNombre(prodDTO.getNombre());
		this.setOrigen(prodDTO.getOrigen());
		this.setPrecio(prodDTO.getPrecio());
		this.setUrlImagen(prodDTO.getUrlImagen());
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDatosExtra() {
		return datosExtra;
	}

	public void setDatosExtra(String datosExtra) {
		this.datosExtra = datosExtra;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
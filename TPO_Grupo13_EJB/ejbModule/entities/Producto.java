package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dto.ProductoDTO;

@Entity
@Table(name = "Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer codigo;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column
	private String marca;
	@Column
	private double precio;
	@Column
	private String urlImagen;
	@Column
	private String origen;
	@Column
	private String datosExtra;
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
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

	public void setPrecio(double d) {
		this.precio = d;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean sosProducto(int codigo) {
		return this.codigo == codigo;
	}

	public ProductoDTO getDTO() {
		ProductoDTO dto = new ProductoDTO();
		dto.setCategoria(categoria.getDTO());
		dto.setCodigo(codigo);
		dto.setDatosExtra(datosExtra);
		dto.setDescripcion(descripcion);
		dto.setMarca(marca);
		dto.setNombre(nombre);
		dto.setOrigen(origen);
		dto.setPrecio(precio);
		dto.setUrlImagen(urlImagen);
		return dto;
	}

}

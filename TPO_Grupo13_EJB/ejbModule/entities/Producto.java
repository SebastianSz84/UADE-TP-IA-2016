package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
	private String foto;
	@Column
	private String origen;
	@Column
	private String datosExtra;
	@Column
	private String tipo;
	@Column
	private Date fechaAlta;

	@OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Ranking Ranking;

	public Producto() {
		super();
	}

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean sosProducto(int codigo) {
		return this.codigo == codigo;
	}

	public ProductoDTO getDTO() {
		ProductoDTO dto = new ProductoDTO();
		dto.setTipo(tipo);
		dto.setCodigo(codigo);
		dto.setDatosExtra(datosExtra);
		dto.setDescripcion(descripcion);
		dto.setMarca(marca);
		dto.setNombre(nombre);
		dto.setOrigen(origen);
		dto.setPrecio(precio);
		dto.setFoto(foto);
		if (this.Ranking != null)
			dto.setRanking(this.Ranking.getPosicion());
		else
			dto.setRanking(0);
		dto.setFechaAlta(fechaAlta);
		return dto;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechAlta) {
		this.fechaAlta = fechAlta;
	}

}

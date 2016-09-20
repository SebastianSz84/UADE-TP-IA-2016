package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	@Column
	private String descripcion;
	@Column
	private String nombre;
	@Column
	private float precio;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
	@Column
	private String caracteristicas;
	@Column
	private String marca;
	@Column
	private String origen;
	@Column
	private Integer rankingVenta;
	@Column
	private Date fecha;
	@Column
	private String urlImagen;
	
		
	public Producto() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public String getCaracteristicas() {
		return caracteristicas;
	}


	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public Integer getRankingVenta() {
		return rankingVenta;
	}


	public void setRankingVenta(int rankingVenta) {
		this.rankingVenta = rankingVenta;
	}

	public boolean sosProducto(int codigo) {
		return this.id == codigo;
	}
	
	public String getBestSellerRanking(){
	   if (this.getRankingVenta() == null)
	      return "N/A";
	   
	   return String.valueOf(this.getRankingVenta());
	}

	public String getImagen() {
		if (this.getUrlImagen() == null)
			return "#";
			   
		return this.getUrlImagen();
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}


	public void setRankingVenta(Integer rankingVenta) {
		this.rankingVenta = rankingVenta;
	}
	
	
	
}

package integracion.dto;

import java.util.Calendar;
import java.util.Map;

import dto.ProductoDTO;

public class ProductoDEDTO {
	private String idDeposito;
	private int codArticulo;
	private String nombre;
	private String descripcion;
	private String marca;
	private float precio;
	private String foto;
	private String origen;
	private String tipo;
	private Map<String, String> datosExtra;

	public String getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(String idDeposito) {
		this.idDeposito = idDeposito;
	}

	public int getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Map<String, String> getDatosExtra() {
		return datosExtra;
	}

	public void setDatosExtra(Map<String, String> datosExtra) {
		this.datosExtra = datosExtra;
	}

	public ProductoDTO toProductoDTO() {
		ProductoDTO prodDTO = new ProductoDTO();

		prodDTO.setCodigo(this.codArticulo);

		String datosExtra = null;

		for (Map.Entry<String, String> entry : this.datosExtra.entrySet()) {
			if (datosExtra == null) {
				datosExtra = entry.getKey() + " " + entry.getValue();
			} else {
				datosExtra = datosExtra + ", " + entry.getKey() + " " + entry.getValue();
			}
		}

		prodDTO.setDatosExtra(datosExtra);

		prodDTO.setDescripcion(this.descripcion);
		prodDTO.setFechaAlta(Calendar.getInstance().getTime());
		prodDTO.setFoto(this.foto);
		prodDTO.setIdDeposito(this.idDeposito);
		prodDTO.setMarca(this.marca);
		prodDTO.setNombre(this.nombre);
		prodDTO.setOrigen(this.origen);
		prodDTO.setPrecio(Double.parseDouble(Float.toString(this.precio)));
		prodDTO.setRanking(0);
		prodDTO.setTipo(this.tipo);

		return prodDTO;
	}

}
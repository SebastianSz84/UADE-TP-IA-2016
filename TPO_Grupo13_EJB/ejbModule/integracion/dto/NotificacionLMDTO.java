package integracion.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotificacionLMDTO {
	private String fecha;
	private String tipo = "Portal";
	private String modulo = "Portal Web Grupo 13";
	private String descripcion;

	public NotificacionLMDTO(String descripcion) {
		super();
		this.fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime());
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
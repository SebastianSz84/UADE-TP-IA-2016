package integracion.dto;

import java.io.Serializable;

public class ClienteLMDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dni;
	private String nombre;
	private String apellido;

	public ClienteLMDTO() {
		super();
	}

	public ClienteLMDTO(String dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
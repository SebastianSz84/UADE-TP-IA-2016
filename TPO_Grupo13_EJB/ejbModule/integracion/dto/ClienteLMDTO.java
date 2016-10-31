package integracion.dto;

import java.io.Serializable;

public class ClienteLMDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int dni;
	private String nombre;
	private String apellido;

	public ClienteLMDTO() {
		super();
	}

	public ClienteLMDTO(int dni, String nombre, String apellido) {
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

	public int getDNI() {
		return dni;
	}

	public void setDNI(int dni) {
		this.dni = dni;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
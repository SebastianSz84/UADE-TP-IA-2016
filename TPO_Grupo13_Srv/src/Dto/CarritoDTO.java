package Dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarritoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private List<ItemCarritoDTO> items;
	private UsuarioDTO usuario;

	public CarritoDTO() {
		super();
		this.items = new ArrayList<ItemCarritoDTO>();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ItemCarritoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemCarritoDTO> items) {
		this.items = items;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
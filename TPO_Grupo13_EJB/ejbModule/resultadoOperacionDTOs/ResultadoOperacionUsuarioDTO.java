package resultadoOperacionDTOs;

import java.io.Serializable;

import dto.UsuarioDTO;

public class ResultadoOperacionUsuarioDTO extends ResultadoOperacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuario;

	public ResultadoOperacionUsuarioDTO(boolean resultado, String message, UsuarioDTO usuario) {
		super(resultado, message);
		// TODO Auto-generated constructor stub
		this.usuario = usuario;
	}

	public UsuarioDTO getUsuario() {
		return this.usuario;
	}
}

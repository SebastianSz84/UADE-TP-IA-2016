package resultadoOperacionDTOs;

import java.io.Serializable;

public class ResultadoOperacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean resultado;
	private String message;

	public ResultadoOperacionDTO(boolean resultado, String message) {
		super();
		this.resultado = resultado;
		this.message = message;
	}

	public boolean sosExitoso() {
		return resultado == true;
	}

	public String getMessage() {
		return message;
	}

}
package resultadoOperacionDTOs;

public class ResultadoOperacionDTO {

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
package resultadoOperacionDTOs;

import java.io.Serializable;

import dto.CarritoDTO;

public class ResultadoOperacionCarritoDTO extends ResultadoOperacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarritoDTO carritoDto;

	public ResultadoOperacionCarritoDTO(boolean resultado, String message, CarritoDTO carritoDto) {
		super(resultado, message);
		this.carritoDto = carritoDto;
	}

	public CarritoDTO getCarritoDTO() {
		return this.carritoDto;
	}

}

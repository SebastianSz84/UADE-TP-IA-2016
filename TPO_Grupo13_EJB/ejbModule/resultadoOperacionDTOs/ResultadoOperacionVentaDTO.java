package resultadoOperacionDTOs;

import java.io.Serializable;

import dto.VentaDTO;

public class ResultadoOperacionVentaDTO extends ResultadoOperacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentaDTO venta;

	public ResultadoOperacionVentaDTO(boolean resultado, String message, VentaDTO venta) {
		super(resultado, message);
		// TODO Auto-generated constructor stub
		this.venta = venta;
	}

	public VentaDTO getVenta() {
		return this.venta;
	}
}

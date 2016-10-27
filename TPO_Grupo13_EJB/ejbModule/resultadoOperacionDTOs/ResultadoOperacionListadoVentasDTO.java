package resultadoOperacionDTOs;

import java.io.Serializable;
import java.util.List;

import dto.VentaDTO;

public class ResultadoOperacionListadoVentasDTO extends ResultadoOperacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VentaDTO> ventas;

	public ResultadoOperacionListadoVentasDTO(boolean resultado, String message, List<VentaDTO> ventas) {
		super(resultado, message);
		this.ventas = ventas;
	}

	public List<VentaDTO> getVentas() {
		return this.ventas;
	}

}
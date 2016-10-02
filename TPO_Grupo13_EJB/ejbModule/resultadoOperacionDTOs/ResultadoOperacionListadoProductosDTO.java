package resultadoOperacionDTOs;

import java.io.Serializable;
import java.util.List;

import dto.ProductoDTO;

public class ResultadoOperacionListadoProductosDTO extends ResultadoOperacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProductoDTO> productos;

	public ResultadoOperacionListadoProductosDTO(boolean resultado, String message, List<ProductoDTO> productos) {
		super(resultado, message);
		this.productos = productos;
	}

	public List<ProductoDTO> getProductos() {
		return this.productos;
	}

}
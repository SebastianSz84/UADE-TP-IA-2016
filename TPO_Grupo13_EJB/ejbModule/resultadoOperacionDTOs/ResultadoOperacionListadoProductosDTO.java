package resultadoOperacionDTOs;

import java.util.List;

import dto.ProductoDTO;

public class ResultadoOperacionListadoProductosDTO extends ResultadoOperacionDTO {

	private List<ProductoDTO> productos;

	public ResultadoOperacionListadoProductosDTO(boolean resultado, String message, List<ProductoDTO> productos) {
		super(resultado, message);
		this.productos = productos;
	}

	public List<ProductoDTO> getProductos() {
		return this.productos;
	}

}
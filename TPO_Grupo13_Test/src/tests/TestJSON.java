package tests;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import dto.ItemVentaDTO;
import dto.ProductoDTO;
import dto.VentaDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

public class TestJSON {

	@Test
	public void getJSONfromAVenta() {
		ProductoDTO producto = new ProductoDTO();
		producto.setCodigo(1857363);
		producto.setDatosExtra("Datos extra");
		producto.setDescripcion("Mesa para TV 42");
		producto.setMarca("Mesas TV SA");
		producto.setNombre("Mesa TV");
		producto.setOrigen("China");
		producto.setPrecio(65.45);
		producto.setFoto(
				"http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png");

		ItemVentaDTO itemDTO = new ItemVentaDTO(12, producto);
		List<ItemVentaDTO> items = new ArrayList<ItemVentaDTO>();
		items.add(itemDTO);

		VentaDTO ventaDTO = new VentaDTO();
		ventaDTO.setEstado("Pendiente");
		ventaDTO.setFecha(new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH));
		ventaDTO.setId(1);
		ventaDTO.setItems(items);
		String mensajeJSON = new Gson().toJson(ventaDTO);
		System.out.println(mensajeJSON);
	}

	@Test
	public void getJSONfromAProducto() {
		ProductoDTO producto = new ProductoDTO();
		producto.setCodigo(1857363);
		producto.setDatosExtra("Datos extra");
		producto.setDescripcion("Mesa para TV 42");
		producto.setMarca("Mesas TV SA");
		producto.setNombre("Mesa TV");
		producto.setOrigen("China");
		producto.setPrecio(65.45);
		producto.setFoto(
				"http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png");
		producto.setTipo("Mueble");

		String mensajeJSON = new Gson().toJson(producto);
		System.out.println(mensajeJSON);
	}

	@Test
	public void getJSONfromAResultadoOperacion() {

		ResultadoOperacionDTO res = new ResultadoOperacionDTO(true, "Actualizacion de estado exitosa");
		String mensajeJSON = new Gson().toJson(res);
		System.out.println(mensajeJSON);

		res = new ResultadoOperacionDTO(true,
				new Exception("Error con el mensaje de la excepcion e.getMessage()").getMessage());
		mensajeJSON = new Gson().toJson(res);
		System.out.println(mensajeJSON);
	}
}
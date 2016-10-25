package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import controllers.BusinessDelegate;
import dto.CarritoDTO;
import dto.ItemCarritoDTO;
import dto.ProductoDTO;
import helpers.ParserJson;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/ServletCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JsonObject jObj = ParserJson.parsearJsonObject(request.getParameter("carrito"));
		JsonArray itemsBS = jObj.getAsJsonArray("items");

		List<ItemCarritoDTO> items = new ArrayList<>();
		for (int i = 0; i < itemsBS.size(); i++) {
			ItemCarritoDTO item = new ItemCarritoDTO();
			ProductoDTO p = new ProductoDTO();
			JsonObject jb = itemsBS.get(i).getAsJsonObject();
			JsonObject jb1 = jb.getAsJsonObject("producto");
			p.setCodigo(jb1.get("codigo").getAsInt());
			p.setDatosExtra(jb1.get("datosExtra").getAsString());
			p.setDescripcion(jb1.get("descripcion").getAsString());
			p.setMarca(jb1.get("marca").getAsString());
			p.setNombre(jb1.get("nombre").getAsString());
			p.setOrigen(jb1.get("origen").getAsString());
			p.setPrecio(jb1.get("precio").getAsDouble());
			p.setFoto(jb1.get("urlImagen").getAsString());
			p.setTipo(jb1.get("tipo").getAsString());

			item.setProducto(p);
			item.setCantidad(jb.get("cantidad").getAsInt());
			items.add(item);
		}
		CarritoDTO dtoC = new CarritoDTO();
		dtoC.setIdUsuario(jObj.get("idUsuario").getAsInt());
		dtoC.setItems(items);
		ResultadoOperacionDTO res = BusinessDelegate.getInstancia().saveCarrito(dtoC);
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf8");
		response.setContentType("application/text");
		if (res == null || !res.sosExitoso()) {
			out.print(res.getMessage());
		} else {
			out.print(res.getMessage());
		}
	}

}

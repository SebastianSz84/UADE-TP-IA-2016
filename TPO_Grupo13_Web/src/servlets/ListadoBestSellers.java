package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.ProductoDTO;
import dto.RankingDTO;
import utils.ParserJson;

/**
 * Servlet implementation class ListadoBestSellers
 */
@WebServlet("/ListadoBestSellers")
public class ListadoBestSellers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoBestSellers() {
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
		JsonObject jObj = ParserJson.parsearJsonObject(request);
		JsonArray itemsBS = jObj.getAsJsonArray("items");

		List<RankingDTO> listaBestSellers = new ArrayList<>();
		for (int i = 0; i < itemsBS.size(); i++) {
			RankingDTO itBS = new RankingDTO();
			ProductoDTO p = new ProductoDTO();
			p.setCodigo(itemsBS.get(i).getAsJsonObject().get("codigo").getAsInt());
			itBS.setProducto(p);
			itBS.setPosicion(itemsBS.get(i).getAsJsonObject().get("posicion").getAsInt());
			listaBestSellers.add(itBS);
		}

		/*
		 * ResultadoOperacionDTO res =
		 * BusinessDelegate.getInstancia().actualizarBestSellers(
		 * listaBestSellers); if (res == null || !res.sosExitoso()) {
		 * response.getWriter().
		 * append("Error al actualizar el listado de Best Sellers."); }
		 */
	}

}
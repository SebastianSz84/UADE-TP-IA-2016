package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import controllers.BusinessDelegate;
import helpers.ParserJson;
import resultadoOperacionDTOs.ResultadoOperacionCarritoDTO;

/**
 * Servlet implementation class ServletGetCarrito
 */
@WebServlet("/ServletGetCarrito")
public class ServletGetCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGetCarrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPos(HttpServletRequest request, HttpServletResponse response)
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
		JsonObject jObj = ParserJson.parsearJsonObject(request);
		int carritoId = jObj.get("userId").getAsInt();
		ResultadoOperacionCarritoDTO res = BusinessDelegate.getInstancia().getCarrito(carritoId);
		if (res.sosExitoso()) {
			String listaGson = new Gson().toJson(res.getCarritoDTO());
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf8");
			response.setContentType("application/json");
			out.print(listaGson);
		} else {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf8");
			response.setContentType("application/text");
			out.print(res.getMessage());
		}
	}

}

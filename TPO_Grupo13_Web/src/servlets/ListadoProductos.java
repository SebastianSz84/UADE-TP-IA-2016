package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.BusinessDelegate;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;

/**
 * Servlet implementation class ListadoProductos
 */
@WebServlet("/ListadoProductos")
public class ListadoProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoProductos() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		ResultadoOperacionListadoProductosDTO res = BusinessDelegate.getInstancia().listadoProductos();
		if (res.getProductos() == null) {
			String listaGson = new Gson().toJson(res.getProductos());
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
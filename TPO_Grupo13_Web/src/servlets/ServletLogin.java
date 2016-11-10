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
import resultadoOperacionDTOs.ResultadoOperacionUsuarioDTO;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLogin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("user");
		String password = request.getParameter("password");
		ResultadoOperacionUsuarioDTO res = BusinessDelegate.getInstancia().login(userName, password);
		if (res.sosExitoso()) {
			String usuario = new Gson().toJson(res.getUsuario());
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf8");
			response.setContentType("application/json");
			out.print(usuario);
		} else {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf8");
			response.setContentType("application/text");
			out.print(res.getMessage());
		}
	}

}

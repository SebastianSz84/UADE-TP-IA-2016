package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.BusinessDelegate;
import dto.ProductoDTO;
import dto.VentaDTO;
import entities.ItemVenta;
import entities.Producto;
import entities.Usuario;
import entities.Venta;
import helpers.ParserJson;
import integracion.dto.VentaLMDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;

/**
 * Servlet implementation class TestInformarVenta
 */
@WebServlet("/TestInformarVenta")
public class TestInformarVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestInformarVenta() {
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

		Venta v = new Venta();
		v.setId(1);
		v.setFecha(new Date());

		Usuario u = new Usuario();
		u.setApellido("sz");
		u.setCoordenadasX("-83.12345");
		u.setCoordenadasY("-67.89012");
		u.setDNI(31303762);
		u.setId(1);
		u.setNombre("seba");
		u.setPassword("pass");
		u.setUserName("user");

		v.setUsuario(u);
		Producto p = new Producto();

		ResultadoOperacionListadoProductosDTO r = BusinessDelegate.getInstancia().listadoProductos();

		for (int i = 0; i < 2; i++) {

			ProductoDTO pr = r.getProductos().get(i);

			p.setId(pr.getId());
			p.setCodigo(pr.getCodigo());
			p.setIdDeposito(pr.getIdDeposito());
			p.setDatosExtra(pr.getDatosExtra());
			p.setDescripcion(pr.getDescripcion());
			p.setMarca(pr.getMarca());
			p.setNombre(pr.getNombre());
			p.setOrigen(pr.getOrigen());
			p.setPrecio(pr.getPrecio());
			p.setFoto(pr.getFoto());
			p.setTipo(pr.getTipo());
			p.setIdDeposito(pr.getIdDeposito());

			v.getItems().add(new ItemVenta(2, p));
		}
		VentaDTO venDTO = v.getDTO();

		VentaLMDTO venLMDTO = venDTO.convertirLMDTO();

		System.out.println(ParserJson.toString(venLMDTO));
	}
}
package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.ParserJson;
import integracion.dto.ProductoDEDTO;

/**
 * Servlet implementation class TestNuevoProductoMDB
 */
@WebServlet("/TestNuevoProductoMDB")
public class TestNuevoProductoMDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestNuevoProductoMDB() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductoDEDTO prodDTO = new ProductoDEDTO();
		prodDTO.setTipo("Tipo");
		prodDTO.setCodArticulo(11);

		Map<String, String> datosExtra = new HashMap<String, String>();
		datosExtra.put("algunDatoExtra", "algunValorExtra");

		prodDTO.setDatosExtra(datosExtra);
		prodDTO.setDescripcion("alguna descripcion");
		prodDTO.setMarca("alguna marca");
		prodDTO.setNombre("algun nombre");
		prodDTO.setOrigen("algun origen");
		prodDTO.setPrecio(123.45678);
		prodDTO.setFoto("alguna url de imagen");
		prodDTO.setIdDeposito("G01");

		String MESSAGE = ParserJson.toString(prodDTO);
		String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
		String DESTINATION = "jms/queue/deposito";

		Context namingContext = null;
		JMSContext context = null;

		try {

			// Set up the namingContext for the JNDI lookup
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			env.put(Context.SECURITY_PRINCIPAL, "guest");
			env.put(Context.SECURITY_CREDENTIALS, "guest");
			namingContext = new InitialContext(env);

			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
			System.out.println("Se obtuvo ConnectionFactory " + CONNECTION_FACTORY);

			Destination destination = (Destination) namingContext.lookup(DESTINATION);
			System.out.println("Se obtuvo JMS Endpoint " + DESTINATION);

			// ACT
			// Create the JMS context
			context = connectionFactory.createContext("guest", "guest");
			context.createProducer().send(destination, MESSAGE);

			// ASSERT
			System.out.println("Mensaje enviado:  " + MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (namingContext != null) {
				try {
					namingContext.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
			if (context != null) {
				context.close();
			}
		}
	}
}
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.BusinessDelegate;
import dto.ProductoDTO;
import helpers.ParserJson;

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

		final String QUEUE_LOOKUP = "jms/queue/deposito";
		final String CONNECTION_FACTORY = "ConnectionFactory";

		PrintWriter out = response.getWriter();
		try {
			Context context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY);
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

			Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
			QueueSender sender = session.createSender(queue);

			TextMessage message = session.createTextMessage();

			ProductoDTO prodDTO = BusinessDelegate.getInstancia().listadoProductos().getProductos().get(0);

			System.out.println(ParserJson.toString(prodDTO));

			message.setText(ParserJson.toString(prodDTO));
			sender.send(message);

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductoDTO prodDTO = new ProductoDTO();
		prodDTO.setTipo("Tipo");
		prodDTO.setCodigo(11);
		prodDTO.setDatosExtra("algunos datos extra");
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
			env.put(Context.PROVIDER_URL, "http-remoting://10.100.56.66:8080");
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
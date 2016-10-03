package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.BusinessDelegate;
import dto.ProductoDTO;
import helpers.XMLHelper;
import integracion.dto.ProdXMLDTO;

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

			ProdXMLDTO resXML = new ProdXMLDTO(prodDTO);

			String resString = XMLHelper.toString(resXML);

			System.out.println(resString);

			message.setText(resString);
			sender.send(message);

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

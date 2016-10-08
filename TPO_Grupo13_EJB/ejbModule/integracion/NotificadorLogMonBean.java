package integracion;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import dto.VentaDTO;
import helpers.ParserJson;
import integracion.interfaces.NotificadorLogMon;

@Stateless
public class NotificadorLogMonBean implements NotificadorLogMon {

	private static Logger logger = Logger.getLogger(NotificadorLogMonBean.class);

	@Override
	public void sincronica(String notif) {
	}

	@Override
	public void asincronica(Object cls) {
		try {
			String ip = Comunicacion.getInstancia().getPropiedad("logAsync", "ip");
			String port = Comunicacion.getInstancia().getPropiedad("logAsync", "puerto");
			String user = Comunicacion.getInstancia().getPropiedad("logAsync", "user");
			String pass = Comunicacion.getInstancia().getPropiedad("logAsync", "pass");
			String cola = "logAsync";

			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://" + ip + ":" + port));
			env.put(Context.SECURITY_PRINCIPAL, System.getProperty("username", user));
			env.put(Context.SECURITY_CREDENTIALS, System.getProperty("password", pass));
			Context context = new InitialContext(env);
			// buscar la Connection Factory en JNDI
			String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
			// buscar la Cola en JNDI
			String destinationString = System.getProperty("destination", cola);
			Destination destination = (Destination) context.lookup(destinationString);
			// crear la connection y la session a partir de la connection
			Connection connection = connectionFactory.createConnection(System.getProperty("username", user),
					System.getProperty("password", pass));
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			// crear un producer para enviar mensajes usando la session
			MessageProducer producer = session.createProducer(destination);

			// Armo el Json que voy a enviar
			String notifJson = ParserJson.toString(cls);
			logger.info("SALIDA ASYNC JSON: \n" + notifJson);
			System.out.print("SALIDA JSON: \n" + notifJson);

			// crear un mensaje de tipo text y setearle el contenido
			TextMessage message = session.createTextMessage();
			message.setText(notifJson);
			// enviar el mensaje
			producer.send(message);
			// Recordar cerrar la session y la connection en un bloque �finally�
			connection.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public String infVenta(VentaDTO venta) {
		try {
			URL url = new URL("http://" + Comunicacion.getInstancia().getPropiedad("informarVenta", "ip") + ":"
					+ Comunicacion.getInstancia().getPropiedad("informarVenta", "puerto") + "/"
					+ Comunicacion.getInstancia().getPropiedad("informarVenta", "url"));

			String mensajeJSON = ParserJson.toString(venta);

			logger.info("SALIDA SINC JSON: \n" + mensajeJSON);
			System.out.print("SALIDA SINC JSON: \n" + mensajeJSON);

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			// urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			// TODO: agregar el m�todo para enviar el json en la conexi�n url.
			urlConnection.getOutputStream().write(mensajeJSON.getBytes());

			String respuesta = urlConnection.getResponseMessage();

			logger.info("++Info respuesta de informar venta sincronico: " + respuesta);

			return respuesta;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al intentar mandar info venta sincronico: " + e.getStackTrace().toString());
			return "Se ha producido un error: " + e.getMessage();
		}
	}
}
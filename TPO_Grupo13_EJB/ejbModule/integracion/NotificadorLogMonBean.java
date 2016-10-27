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

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import dto.VentaDTO;
import helpers.ParserJson;
import integracion.interfaces.Configuracion;
import integracion.interfaces.NotificadorLogMon;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

@Stateless
public class NotificadorLogMonBean implements NotificadorLogMon {

	private static Logger logger = Logger.getLogger(NotificadorLogMonBean.class);

	@Override
	public ResultadoOperacionDTO sincronica(String notif, Configuracion conf) {
		try {
			URL url = new URL("http://" + conf.getIp() + ":" + conf.getPuerto() + "/" + conf.getUrl());
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");

			String mensajeJSON = ParserJson.toString(notif);

			logger.info("SALIDA SINC JSON: \n" + mensajeJSON);
			System.out.print("SALIDA SINC JSON: \n" + mensajeJSON);

			IOUtils.write(mensajeJSON, urlConnection.getOutputStream());
			if (urlConnection.getResponseCode() != 200) {
				String respuesta = "++ERROR: " + urlConnection.getResponseCode();
				logger.error(respuesta);
				return new ResultadoOperacionDTO(false, respuesta);
			}

			String respuesta = IOUtils.toString(urlConnection.getInputStream());
			logger.info("++Info respuesta de informar venta sincronico: " + respuesta);
			return new ResultadoOperacionDTO(true, "Respuesta de Informar Venta: " + respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al intentar mandar info venta sincronico: " + e.getStackTrace().toString());
			return new ResultadoOperacionDTO(false, "Se ha producido un error: " + e.getMessage());
		}
	}

	@Override
	public ResultadoOperacionDTO asincronica(String notif, Configuracion conf) {
		try {
			String ip = conf.getIp();
			String port = conf.getPuerto();
			String user = conf.getUser();
			String pass = conf.getPass();

			final Properties env = new Properties();
			// env.put(Context.INITIAL_CONTEXT_FACTORY,
			// "org.jboss.naming.remote.client.InitialContextFactory");
			// env.put(Context.PROVIDER_URL,
			// System.getProperty(Context.PROVIDER_URL, "http-remoting://" + ip
			// + ":" + port));
			// env.put(Context.SECURITY_PRINCIPAL,
			// System.getProperty("username", user));
			// env.put(Context.SECURITY_CREDENTIALS,
			// System.getProperty("password", pass));

			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "http-remoting://" + ip + ":" + port);
			env.put(Context.SECURITY_PRINCIPAL, user);
			env.put(Context.SECURITY_CREDENTIALS, pass);

			Context context = new InitialContext(env);
			// buscar la Connection Factory en JNDI
			String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
			// buscar la Cola en JNDI
			String destinationString = System.getProperty("destination", "jms/queue/logmonasync");
			Destination destination = (Destination) context.lookup(destinationString);
			// crear la connection y la session a partir de la connection
			Connection connection = connectionFactory.createConnection(user, pass);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			// crear un producer para enviar mensajes usando la session
			MessageProducer producer = session.createProducer(destination);

			// Armo el Json que voy a enviar
			String notifJson = ParserJson.toString(notif);
			logger.info("SALIDA ASYNC JSON: \n" + notifJson);
			System.out.print("SALIDA JSON: \n" + notifJson);

			// crear un mensaje de tipo text y setearle el contenido
			TextMessage message = session.createTextMessage();
			message.setText(notifJson);
			// enviar el mensaje
			producer.send(message);
			// se cierra la sesion y devuelve mensaje de exito
			connection.close();
			return new ResultadoOperacionDTO(true, "Operacion correcta: " + notif);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoOperacionDTO(false, "Error en notificacion asincronica: " + e.getMessage());
		}
	}

	@Override
	public ResultadoOperacionDTO infVenta(VentaDTO venta, Configuracion conf) {
		try {
			URL url = new URL("http://" + conf.getIp() + ":" + conf.getPuerto() + "/" + conf.getUrl());

			String mensajeJSON = ParserJson.toString(venta);

			logger.info("SALIDA SINC JSON: \n" + mensajeJSON);
			System.out.print("SALIDA SINC JSON: \n" + mensajeJSON);

			// TODO: nos tienen que pasar el WSDL para generar la interfaz
			// contra LogMon.
			// LogisticaMonitoreoWS port = new
			// LogisticaMonitoreoBeanService(url).getLogisticaMonitoreoWSPort();
			// String respuesta = port.infVenta(mensajeJSON);

			String respuesta = "";

			logger.info("++Info respuesta de informar venta sincronico: " + respuesta);

			return new ResultadoOperacionDTO(true, "Respuesta de Informar Venta: " + respuesta);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al intentar mandar info venta sincronico: " + e.getStackTrace().toString());
			return new ResultadoOperacionDTO(false, "Se ha producido un error: " + e.getMessage());
		}
	}
}
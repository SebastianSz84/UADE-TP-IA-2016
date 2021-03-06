package tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import helpers.ParserJson;
import integracion.dto.ProductoDEDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

public class ServicesTests {

	@Test
	public void ActualizarBestSellersOK() {
		try {

			// ARRANGE
			URL url = new URL("http://localhost:8080/TPO_Grupo13_Web/rest/bestSellers/actualizar");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");

			// ACT
			IOUtils.write(
					"{\"ranking\":[{\"codigo\":\"1858018\",\"nombreDeposito\":\"G01\",\"posicion\":1},{\"codigo\":\"1858013\",\"nombreDeposito\":\"G01\",\"posicion\":2}]}",
					urlConnection.getOutputStream());
			if (urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexi�n: " + urlConnection.getResponseCode());
			}

			// ASSERT
			String response = IOUtils.toString(urlConnection.getInputStream());
			System.out.println("Respuesta: " + response);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void TestNuevoProductoMDB() {
		// ARRANGE

		// Armo el dto que voy a enviar
		ProductoDEDTO prodDTO = new ProductoDEDTO();
		prodDTO.setTipo("Tipo");
		prodDTO.setCodArticulo(11);
		Map<String, String> datosExtra = new HashedMap();

		datosExtra.putIfAbsent("claveDatoExtra1", "valor de datoExtra1");
		datosExtra.putIfAbsent("claveDatoExtra2", "valor de datoExtra2");

		prodDTO.setDatosExtra(datosExtra);
		prodDTO.setDescripcion("alguna descripcion2");
		prodDTO.setMarca("alguna marca");
		prodDTO.setNombre("algun nombre");
		prodDTO.setOrigen("algun origen");
		prodDTO.setPrecio((float) 123.45678);
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
			env.put(Context.PROVIDER_URL, "http-remoting://192.168.1.33:8080");
			env.put(Context.SECURITY_PRINCIPAL, "coladeposito");
			env.put(Context.SECURITY_CREDENTIALS, "password123");
			namingContext = new InitialContext(env);

			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
			System.out.println("Se obtuvo ConnectionFactory " + CONNECTION_FACTORY);

			Destination destination = (Destination) namingContext.lookup(DESTINATION);
			System.out.println("Se obtuvo JMS Endpoint " + DESTINATION);

			// ACT
			// Create the JMS context
			context = connectionFactory.createContext("coladeposito", "password123");
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

	@Test
	public void TestLogMonAsync() {
		// ARRANGE

		// Armo el dto que voy a enviar
		ResultadoOperacionDTO res = new ResultadoOperacionDTO(true, "Llego el mensaje?");

		String MESSAGE = ParserJson.toString(res);
		String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
		String DESTINATION = "jms/queue/logmonasync";

		Context namingContext = null;
		JMSContext context = null;

		try {

			// Set up the namingContext for the JNDI lookup
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "http-remoting://sebalaptop:8080");
			env.put(Context.SECURITY_PRINCIPAL, "guest");
			env.put(Context.SECURITY_CREDENTIALS, "guest");
			namingContext = new InitialContext(env);

			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
			System.out.println("Se obtuvo ConnectionFactory " + CONNECTION_FACTORY);

			Destination destination = (Destination) namingContext.lookup(DESTINATION);
			System.out.println("Se obtuvo JMS Endpoint " + DESTINATION);

			// ACT
			// Create the JMS context
			context = connectionFactory.createContext("grupo13", "grupo13");
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

	@Test
	public void TestEstadoDespacho() {
		try {

			// ARRANGE
			URL url = new URL("http://localhost:8080/TPO_Grupo13_Web/rest/despacho/actualizarEstado");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");

			// ACT
			IOUtils.write("{\"numeroVenta\":1, \"estado\":\"Entregada\"}", urlConnection.getOutputStream());
			if (urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexi�n: " + urlConnection.getResponseCode());
			}

			// ASSERT
			String response = IOUtils.toString(urlConnection.getInputStream());
			System.out.println("Respuesta: " + response);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
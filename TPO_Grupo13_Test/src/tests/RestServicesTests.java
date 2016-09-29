package tests;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class RestServicesTests {

	@Test
	public void ActualizarBestSellersOK() {
		URL url;
		try {
			url = new URL("http://localhost:8080/TPO_Grupo13_Web/rest/bestSellers/actualizar");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			IOUtils.write("{\"ranking\":[{\"codigo\":\"1\",\"posicion\":\"3\"}]}", urlConnection.getOutputStream());
			if (urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
			}
			String response = IOUtils.toString(urlConnection.getInputStream());
			System.out.println("Respuesta: " + response);
		} catch (Exception ex) {
		}
	}
}
package tests;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ServicesTests {

	@Test
	public void ActualizarBestSellersOK() {
		URL url;
		try {

			// ARRANGE
			url = new URL("http://localhost:8080/TPO_Grupo13_Web/rest/bestSellers/actualizar");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");

			// ACT
			IOUtils.write("{\"ranking\":[{\"codigo\":\"1857363\",\"posicion\":\"3\"}]}",
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
}
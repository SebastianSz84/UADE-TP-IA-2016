package tests;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class PruebaRest {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/TPO_Grupo13_Web/rest/bestSellers/actualizar");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "text/plain");
		IOUtils.write("Juanito", urlConnection.getOutputStream());
		if (urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}
		String response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
	}
}
package integracion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracionComunicacion {

	private static ConfiguracionComunicacion instancia;
	private Properties props;
	private static String propFileName = "config.properties";
	private InputStream inputStream;

	private ConfiguracionComunicacion() throws IOException {
		try {
			props = new Properties();

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("Archivo de propiedades '" + propFileName + "' no encontrado.");
			}

		} catch (Exception e) {
			System.out.println("Error al leer la configuración: " + e.getMessage());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public static ConfiguracionComunicacion getInstancia() {
		if (instancia == null) {
			try {
				instancia = new ConfiguracionComunicacion();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}

	public String getPropiedad(String nombre) {
		if (props != null) {
			return props.getProperty(nombre);
		}
		return "";
	}
}
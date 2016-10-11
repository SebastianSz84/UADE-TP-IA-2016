package integracion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import integracion.interfaces.Configuracion;

public class Comunicacion {

	private static Comunicacion instancia;
	private Properties props;
	private static String propFileName = "config.properties";
	private InputStream inputStream;

	private Comunicacion() throws IOException {
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

	public static Comunicacion getInstancia() {
		if (instancia == null) {
			try {
				instancia = new Comunicacion();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}

	public Configuracion getConfiguracion(String funcionalidad) {
		if (props == null) {
			return null;
		}

		ConfiguracionBean conf = new ConfiguracionBean();
		conf.setIp(getPropiedad("ip"));
		conf.setPass(getPropiedad("pass"));
		conf.setPuerto(getPropiedad("puerto"));
		conf.setUrl(getPropiedad("url"));
		conf.setUser(getPropiedad("user"));
		return conf;
	}

	private String getPropiedad(String nombre) {
		if (props != null) {
			return props.getProperty(nombre);
		}
		return "";
	}
}
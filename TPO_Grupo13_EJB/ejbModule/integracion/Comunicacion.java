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

		if ("informarVenta".equals(funcionalidad)) {
			String id = props.getProperty(funcionalidad);
		} else if ("log".equals(funcionalidad)) {
			String id = props.getProperty("logAsync");
			if (!Boolean.valueOf(props.getProperty("logAsync", "activa" + id))) {
				id = props.getProperty("logSync");
			}
		}

		conf.setFuncionalidad(funcionalidad);
		conf.setIp(getPropiedad(funcionalidad, "ip"));
		conf.setPass(getPropiedad(funcionalidad, "pass"));
		conf.setPuerto(getPropiedad(funcionalidad, "puerto"));
		conf.setTipo(getPropiedad(funcionalidad, "tipo"));
		conf.setUrl(getPropiedad(funcionalidad, "url"));
		conf.setUser(getPropiedad(funcionalidad, "user"));
		return conf;
	}

	private String getPropiedad(String funcionalidad, String nombre) {
		if (props != null) {
			return props.getProperty(nombre);
		}
		return "";
	}
}
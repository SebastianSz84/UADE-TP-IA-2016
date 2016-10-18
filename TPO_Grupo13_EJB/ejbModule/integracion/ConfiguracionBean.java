package integracion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ejb.Singleton;

import integracion.interfaces.Configuracion;

/**
 * Session Bean implementation class ConfiguracionBean
 */
@Singleton
public class ConfiguracionBean implements Configuracion {

	private String ip;
	private String puerto;
	private String user;
	private String pass;
	private String url;
	private String tipo;
	private Properties props;
	private static String propFileName = "config.properties";
	private InputStream inputStream;

	/**
	 * Default constructor.
	 */
	public ConfiguracionBean() {
		try {
			leerConfiguracion();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void leerConfiguracion() throws IOException {
		try {
			props = new Properties();

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("Archivo de propiedades '" + propFileName + "' no encontrado.");
			}

			if (props != null) {
				this.setIp(getPropiedad("ip"));
				this.setPass(getPropiedad("pass"));
				this.setPuerto(getPropiedad("puerto"));
				this.setUrl(getPropiedad("url"));
				this.setUser(getPropiedad("user"));
				this.setTipo(getPropiedad("tipo"));
			}
		} catch (Exception e) {
			System.out.println("Error al leer la configuración: " + e.getMessage());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	private String getPropiedad(String nombre) {
		if (props != null) {
			return props.getProperty(nombre);
		}
		return "";
	}

	@Override
	public String getIp() {
		return ip;
	}

	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String getPuerto() {
		return puerto;
	}

	@Override
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String getPass() {
		return pass;
	}

	@Override
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return ip + "\n" + puerto + "\n" + user + "\n" + pass + "\n" + url;
	}

	@Override
	public String getTipo() {
		return tipo;
	}

	@Override
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
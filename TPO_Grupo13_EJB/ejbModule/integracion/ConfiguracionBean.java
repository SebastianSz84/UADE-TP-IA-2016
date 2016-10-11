package integracion;

import javax.ejb.Stateless;

import integracion.interfaces.Configuracion;

/**
 * Session Bean implementation class ConfiguracionBean
 */
@Stateless
public class ConfiguracionBean implements Configuracion {

	private String ip;
	private String puerto;
	private String user;
	private String pass;
	private String url;

	/**
	 * Default constructor.
	 */
	public ConfiguracionBean() {
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

	public String toString() {
		return ip + "\n" + puerto + "\n" + user + "\n" + pass + "\n" + url;
	}
}
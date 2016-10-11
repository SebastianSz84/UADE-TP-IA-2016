package integracion.interfaces;

import javax.ejb.Local;

@Local
public interface Configuracion {

	String getIp();

	void setIp(String ip);

	String getPuerto();

	void setPuerto(String puerto);

	String getUser();

	void setUser(String user);

	String getPass();

	void setPass(String pass);

	String getUrl();

	void setUrl(String url);

}
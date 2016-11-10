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

	String getTipo();

	void setTipo(String tipo);

	String getRestAudit();

	void setRestAudit(String restAudit);

	String getInfVenta();

	void setInfVenta(String infVenta);

	String getJmsAudit();

	void setJmsAudit(String jmsAudit);
}
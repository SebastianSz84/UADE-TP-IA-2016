package integracion.interfaces;

import javax.ejb.Local;

import dto.VentaDTO;
import integracion.ConfiguracionComunicacion;

@Local
public interface NotificadorLogMon {
	public void sincronica(String notif, ConfiguracionComunicacion conf);

	public void asincronica(String notif, ConfiguracionComunicacion conf);

	public String infVenta(VentaDTO venta, ConfiguracionComunicacion conf);
}
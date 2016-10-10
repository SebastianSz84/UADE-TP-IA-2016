package integracion.interfaces;

import javax.ejb.Local;

import dto.VentaDTO;

@Local
public interface NotificadorLogMon {
	public void sincronica(String notif, Configuracion conf);

	public void asincronica(Object cls, Configuracion conf);

	public String infVenta(VentaDTO venta, Configuracion conf);
}
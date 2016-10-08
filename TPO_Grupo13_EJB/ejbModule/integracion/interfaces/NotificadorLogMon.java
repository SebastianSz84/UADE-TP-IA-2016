package integracion.interfaces;

import javax.ejb.Local;

import dto.VentaDTO;

@Local
public interface NotificadorLogMon {
	public void sincronica(String notif);

	public void asincronica(Object cls);

	public String infVenta(VentaDTO venta);
}
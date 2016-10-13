package integracion.interfaces;

import javax.ejb.Local;

import dto.VentaDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

@Local
public interface NotificadorLogMon {
	public ResultadoOperacionDTO sincronica(String notif, Configuracion conf);

	public ResultadoOperacionDTO asincronica(String notif, Configuracion conf);

	public ResultadoOperacionDTO infVenta(VentaDTO venta, Configuracion conf);
}
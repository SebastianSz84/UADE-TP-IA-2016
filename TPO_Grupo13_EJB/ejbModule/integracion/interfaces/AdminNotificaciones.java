package integracion.interfaces;

import javax.ejb.Local;

import dto.VentaDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

@Local
public interface AdminNotificaciones {
	public ResultadoOperacionDTO enviarInfoVenta(VentaDTO venta);

	public ResultadoOperacionDTO enviarNotificacion(String operacion);
}
package integracion;

import javax.ejb.Stateless;

import dto.VentaDTO;
import integracion.interfaces.AdminNotificaciones;
import integracion.interfaces.NotificadorLogMon;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

/**
 * Session Bean implementation class AdminNotificaciones
 */
@Stateless
public class AdminNotificacionesBean implements AdminNotificaciones {

	private NotificadorLogMon notificador;

	/**
	 * Default constructor.
	 */
	public AdminNotificacionesBean() {
	}

	@Override
	public ResultadoOperacionDTO enviarInfoVenta(VentaDTO venta) {

		return null;
	}

	@Override
	public ResultadoOperacionDTO enviarNotificacion(String operacion) {
		return null;
	}
}
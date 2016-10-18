package integracion;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dto.VentaDTO;
import helpers.ParserJson;
import integracion.interfaces.AdminNotificaciones;
import integracion.interfaces.Configuracion;
import integracion.interfaces.NotificadorLogMon;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

/**
 * Session Bean implementation class AdminNotificaciones
 */
@Stateless
public class AdminNotificacionesBean implements AdminNotificaciones {

	@EJB
	private NotificadorLogMon notificador;
	@EJB
	private Configuracion configuracion;

	/**
	 * Default constructor.
	 */
	public AdminNotificacionesBean() {
	}

	@Override
	public ResultadoOperacionDTO enviarInfoVenta(VentaDTO venta) {
		return notificador.sincronica(ParserJson.toString(venta), configuracion);
	}

	@Override
	public ResultadoOperacionDTO enviarNotificacion(String notif) {
		if (configuracion == null) {
			return new ResultadoOperacionDTO(false, "Error al abrir el archivo de configuracion");
		} else {
			if (configuracion.getTipo().equals("Async")) {
				return notificador.asincronica(notif, configuracion);
			} else {
				return notificador.sincronica(notif, configuracion);
			}
		}
	}
}
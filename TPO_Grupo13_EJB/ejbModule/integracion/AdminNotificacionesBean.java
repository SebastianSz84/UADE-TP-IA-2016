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

	/**
	 * Default constructor.
	 */
	public AdminNotificacionesBean() {
	}

	@Override
	public ResultadoOperacionDTO enviarInfoVenta(VentaDTO venta) {
		Configuracion conf = Comunicacion.getInstancia().getConfiguracion();

		return notificador.sincronica(ParserJson.toString(venta), conf);
	}

	@Override
	public ResultadoOperacionDTO enviarNotificacion(String notif) {
		Configuracion conf = Comunicacion.getInstancia().getConfiguracion();

		if (conf == null) {
			return new ResultadoOperacionDTO(false, "Error al abrir el archivo de configuracion");
		} else {
			if (conf.getTipo().equals("Async")) {
				return notificador.asincronica(notif, conf);
			} else {
				return notificador.sincronica(notif, conf);
			}
		}
	}
}
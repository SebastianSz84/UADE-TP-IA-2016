package integracion;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import dto.VentaDTO;
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

		notificador.sincronica(new Gson().toJson(venta), conf);

		return null;
	}

	@Override
	public ResultadoOperacionDTO enviarNotificacion(String operacion) {
		Configuracion conf = Comunicacion.getInstancia().getConfiguracion();

		if (conf.getTipo().equals("Async")) {
			notificador.asincronica(operacion, conf);
		} else {
			notificador.sincronica(operacion, conf);
		}

		return null;
	}
}
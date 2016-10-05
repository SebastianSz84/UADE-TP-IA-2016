package integracion;

import java.net.URL;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dto.VentaDTO;
import integracion.interfaces.NotificadorLogMon;

@Stateless
public class NotificadorLogMonBean implements NotificadorLogMon {

	private static Logger logger = Logger.getLogger(NotificadorLogMonBean.class);

	@Override
	public void sincronica(String notif, ConfiguracionComunicacion conf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asincronica(String notif, ConfiguracionComunicacion conf) {

	}

	@Override
	public String infVenta(VentaDTO venta, ConfiguracionComunicacion conf) {
		try {
			URL url = new URL("http://" + conf.getPropiedad("informarVenta", "ip") + ":"
					+ conf.getPropiedad("informarVenta", "puerto") + "/" + conf.getPropiedad("informarVenta", "url")
					+ "?wsdl");

			String mensajeJSON = new Gson().toJson(venta);

			logger.info("SALIDA SINC JSON: \n" + mensajeJSON);
			System.out.print("SALIDA SINC JSON: \n" + mensajeJSON);

			// Le paso la url dinamica de la ubicacion del wsdl
			// LogisticaMonitoreoWS port = new
			// LogisticaMonitoreoBeanService(url).getLogisticaMonitoreoWSPort();

			// String respuesta = port.informarVenta(mensajeXML);

			// logger.info("++Info respuesta de informar venta sincronico: " +
			// respuesta);

			// return respuesta;
			return "dummy infVenta";

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("++Error al intentar mandar info venta sincronico: " + e.getStackTrace().toString());
			return "Se ha producido un error: " + e.getMessage();
		}
	}
}
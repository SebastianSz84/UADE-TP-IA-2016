package integracion;

import java.io.StringWriter;
import java.net.URL;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import dto.VentaDTO;
import integracion.dto.VentaDTOXML;
import integracion.interfaces.NotificadorLogMon;

@Stateless
public class NotificadorLogMonBean implements NotificadorLogMon {

	@Override
	public void sincronica(String notif, ConfiguracionComunicacion conf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asincronica(String notif, ConfiguracionComunicacion conf) {
		// TODO Auto-generated method stub

	}

	@Override
	public String infVenta(VentaDTO venta, ConfiguracionComunicacion conf) {
		try {
			URL url = new URL("http://" + conf.getPropiedad("informarVenta", "ip") + ":"
					+ conf.getPropiedad("informarVenta", "puerto") + "/" + conf.getPropiedad("informarVenta", "url")
					+ "?wsdl");
			String mensajeXML = this.getVentaXML(venta);

			// logger.info("SALIDA SINC XML: \n" + mensajeXML);
			System.out.print("SALIDA SINC XML: \n" + mensajeXML);

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
			// logger.error("++Error al intentar mandar info venta sincronico: "
			// + e.getStackTrace().toString());
			return "Se ha producido un error: " + e.getMessage();
		}
	}

	private String getVentaXML(VentaDTO venta) throws Exception {
		VentaDTOXML venXML = new VentaDTOXML(venta);
		JAXBContext jc = JAXBContext.newInstance(VentaDTOXML.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter writer = new StringWriter();
		m.marshal(venXML, writer);
		return writer.toString();
	}
}
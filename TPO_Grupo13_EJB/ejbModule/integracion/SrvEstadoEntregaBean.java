package integracion;

import java.io.StringWriter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import controllers.interfaces.Controlador;
import integracion.dto.ResEntregaXMLDTO;
import integracion.interfaces.SrvEstadoEntrega;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

@Stateless
@WebService
public class SrvEstadoEntregaBean implements SrvEstadoEntrega {

	@EJB
	private Controlador controlador;

	public SrvEstadoEntregaBean() {
	}

	@WebMethod
	public String notificarEntregaDespacho(int numeroVenta) {
		ResultadoOperacionDTO res = controlador.actualizarEstadoVenta(numeroVenta);
		ResEntregaXMLDTO resXML = new ResEntregaXMLDTO(res.sosExitoso() == true, res.getMessage());
		if (resXML.sosExitoso()) {
			return this.getResultadoXML(resXML);
		} else {
			return this.getResultadoXML(resXML);
		}
	}

	private String getResultadoXML(ResEntregaXMLDTO resXML) {
		try {
			JAXBContext jc = JAXBContext.newInstance(ResultadoOperacionDTO.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter writer = new StringWriter();
			m.marshal(resXML, writer);
			return writer.toString();
		} catch (JAXBException e) {
			// logger.error("Error al marshallear Resultado: " +
			// e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
}
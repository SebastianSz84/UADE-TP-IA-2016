package tests;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import integracion.dto.ResEntregaXMLDTO;

public class TestXML {

	public static void main(String[] args) {
		TestXML t = new TestXML();
		ResEntregaXMLDTO resXML = new ResEntregaXMLDTO(true, "Actualizacion de estado exitosa.");
		System.out.println(t.getResultadoXML(resXML));
	}

	public TestXML() {
		super();
	}

	public String getResultadoXML(ResEntregaXMLDTO resXML) {
		try {
			JAXBContext jc = JAXBContext.newInstance(ResEntregaXMLDTO.class);
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
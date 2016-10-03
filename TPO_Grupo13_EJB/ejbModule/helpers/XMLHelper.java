package helpers;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLHelper {

	public static <T> String toString(T xml) {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(xml.getClass());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter writer = new StringWriter();
			m.marshal(xml, writer);
			return writer.toString();
		} catch (JAXBException e) {
			return "";
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T toXML(Class<T> cls, String xml) {
		try {
			return (T) JAXBContext.newInstance(cls).createUnmarshaller().unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			return (T) "<>";
		}
	}
}

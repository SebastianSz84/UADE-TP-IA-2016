package integracion;

import java.io.StringReader;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;

import controllers.interfaces.Controlador;
import integracion.dto.ProdXMLDTO;

/**
 * Message-Driven Bean implementation class for: NuevoProductoMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/deposito"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "deposito")
public class NuevoProductoMDB implements MessageListener {

	@EJB
	private Controlador controladorBean;

	/**
	 * Default constructor.
	 */
	public NuevoProductoMDB() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			String nuevoProdXML = ((TextMessage) message).getText();
			ProdXMLDTO prodDTO = (ProdXMLDTO) JAXBContext.newInstance(ProdXMLDTO.class).createUnmarshaller()
					.unmarshal(new StringReader(nuevoProdXML));
			controladorBean.nuevoProducto(prodDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
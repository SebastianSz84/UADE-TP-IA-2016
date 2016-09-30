package integracion;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import controllers.interfaces.Controlador;
import helpers.XMLHelper;
import integracion.dto.ProdXMLDTO;

/**
 * Message-Driven Bean implementation class for: NuevoProductoMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/deposito"),
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
			ProdXMLDTO prodXMLDTO = XMLHelper.toXML(ProdXMLDTO.class, nuevoProdXML);
			controladorBean.nuevoProducto(prodXMLDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
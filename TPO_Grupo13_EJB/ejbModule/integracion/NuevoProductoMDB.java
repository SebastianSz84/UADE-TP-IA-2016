package integracion;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import controllers.interfaces.Controlador;
import helpers.ParserJson;
import integracion.dto.ProductoDEDTO;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

/**
 * Message-Driven Bean implementation class for: NuevoProductoMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/deposito"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/jboss/exported/jms/queue/deposito")
public class NuevoProductoMDB implements MessageListener {

	@EJB
	private Controlador controladorBean;

	private static Logger logger = Logger.getLogger(NuevoProductoMDB.class);

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
			logger.info("++ NuevoProductoMDB: Llego un nuevo producto.");
			String nuevoProdJSON = ((TextMessage) message).getText();
			ProductoDEDTO prodDEDTO = ParserJson.fromJson(nuevoProdJSON, ProductoDEDTO.class);
			ResultadoOperacionDTO res = controladorBean.nuevoProducto(prodDEDTO.toProductoDTO());
			if (res.sosExitoso()) {
				logger.info("++ Nuevo producto agregado: " + res.getMessage());
			} else {
				logger.error("++ Error en nuevo producto: " + res.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("++ Error en nuevo producto: " + e.getMessage());
		}
	}
}
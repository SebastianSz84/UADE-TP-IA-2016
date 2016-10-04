package integracion;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import controllers.interfaces.Controlador;
import helpers.XMLHelper;
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
	public String notificarEstadoDespacho(int numeroVenta, String estado) {
		ResultadoOperacionDTO res = controlador.actualizarEstadoVenta(numeroVenta, estado);
		ResEntregaXMLDTO resXML = new ResEntregaXMLDTO(res.sosExitoso(), res.getMessage());

		return XMLHelper.toString(resXML);
	}
}
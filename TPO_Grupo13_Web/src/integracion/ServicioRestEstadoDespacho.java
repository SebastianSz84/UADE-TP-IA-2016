package integracion;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.google.gson.JsonObject;

import controllers.BusinessDelegate;
import helpers.ParserJson;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

@Path("/despacho")
public class ServicioRestEstadoDespacho {

	private static Logger logger = Logger.getLogger(ServicioRestEstadoDespacho.class);

	@POST
	@Path("/actualizarEstado")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String actualizarEstado(String jsonEstadoDespacho) {
		try {
			JsonObject jObj = ParserJson.parsearJsonObject(jsonEstadoDespacho);
			int numeroVenta = jObj.get("numeroVenta").getAsInt();
			String estado = jObj.get("estado").getAsString();

			ResultadoOperacionDTO res = BusinessDelegate.getInstancia().actualizarEstadoVenta(numeroVenta, estado);

			if (res == null || !res.sosExitoso()) {
				logger.error("Error en actualizacion: " + res.getMessage());
				return "{\"estado\":\"ERROR\", \"mensaje\":\"" + res.getMessage() + "\"}";
			} else {
				logger.info("Actualizacion exitosa");
				return "{\"estado\":\"OK\", \"mensaje\":\"Actualizacion exitosa\"}";
			}
		} catch (Exception e) {
			logger.error("Error en actualizacion: " + e.getMessage());
			return "{\"estado\":\"ERROR\", \"mensaje\":\"" + e.getMessage() + "\"}";
		}
	}
}
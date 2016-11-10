package integracion;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import controllers.BusinessDelegate;
import dto.ProductoDTO;
import dto.RankingDTO;
import helpers.ParserJson;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

@Path("/bestSellers")
public class ServicioRestBestSellers {

	private static Logger logger = Logger.getLogger(ServicioRestBestSellers.class);

	@POST
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String bestSellers(String jsonBestSellers) {
		try {
			JsonObject jObj = ParserJson.parsearJsonObject(jsonBestSellers);
			JsonArray itemsBS = jObj.getAsJsonArray("ranking");

			List<RankingDTO> listaBestSellers = new ArrayList<>();
			for (int i = 0; i < itemsBS.size(); i++) {
				RankingDTO itBS = new RankingDTO();
				ProductoDTO p = new ProductoDTO();
				p.setCodigo(itemsBS.get(i).getAsJsonObject().get("codigo").getAsInt());
				itBS.setProducto(p);
				itBS.setPosicion(itemsBS.get(i).getAsJsonObject().get("posicion").getAsInt());
				listaBestSellers.add(itBS);
			}

			ResultadoOperacionDTO res = BusinessDelegate.getInstancia().actualizarBestSellers(listaBestSellers);
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
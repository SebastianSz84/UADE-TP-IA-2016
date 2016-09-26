package controllers;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;

import controllers.interfaces.Controlador;
import resultadoOperacionDTOs.ResultadoOperacionDTO;
import resultadoOperacionDTOs.ResultadoOperacionListadoProductosDTO;

public class BusinessDelegate {

	private static BusinessDelegate instancia;
	private Controlador controlador;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private BusinessDelegate() {
		try {
			final Hashtable props = new Hashtable();
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new javax.naming.InitialContext(props);
			controlador = (Controlador) context.lookup("ejb:" + "TPO_Grupo13_EAR" + "/" + "TPO_Grupo13_EJB" + "/" + ""
					+ "/" + "ControladorBean" + "!" + Controlador.class.getName());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BusinessDelegate getInstancia() {
		if (instancia == null) {
			instancia = new BusinessDelegate();
		}
		return instancia;
	}

	public ResultadoOperacionDTO altaUsuario(String nombre, String apellido, String userName, String password) {
		try {
			return controlador.altaUsuario(nombre, apellido, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultadoOperacionListadoProductosDTO listadoProductos() {
		try {
			return controlador.listadoProductos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
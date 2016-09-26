package controllers;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;

import controllers.interfaces.IControlador;
import resultadoOperacionDTOs.ResultadoOperacionDTO;

public class BusinessDelegate {

	private static BusinessDelegate instancia;
	private IControlador controlador;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private BusinessDelegate() {
		try {
			final Hashtable props = new Hashtable();
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new javax.naming.InitialContext(props);
			controlador = (IControlador) context.lookup("ejb:" + "TPO_Grupo13_EAR" + "/" + "TPO_Grupo13_EJB" + "/" + ""
					+ "/" + "Controlador" + "!" + IControlador.class.getName());
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
}
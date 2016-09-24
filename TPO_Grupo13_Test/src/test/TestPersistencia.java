package test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

public class TestPersistencia {

	public static void main(String[] args) {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "TPO_Grupo13_EAR"; // Nombre del módulo EAR
		final String ejbModuleName = "TPO_Grupo13_EJB"; // Nombre del módulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones
										// en nombres
		final String ejbClassName = "UsuarioDAOBean"; // Nombre corto del
														// EJB
		final String fullInterfaceName = StatelessHelloServiceRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		StatelessHelloServiceRemote mbr = (StatelessHelloServiceRemote) context.lookup(lookupName);
		System.out.println("Respuesta: " + mbr.sayHello("Mr Bean"));
	}
}
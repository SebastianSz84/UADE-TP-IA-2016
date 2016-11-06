package tests;

import org.jgroups.Message;

import integracion.NuevoProductoMDB;

public class JMS {

	NuevoProductoMDB nuevoProdMDB;

	public void main() {
		nuevoProdMDB.onMessage((javax.jms.Message) new Message());
	}
}

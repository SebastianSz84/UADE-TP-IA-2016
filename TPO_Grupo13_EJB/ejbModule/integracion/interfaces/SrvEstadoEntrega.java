package integracion.interfaces;

import javax.ejb.Remote;

@Remote
public interface SrvEstadoEntrega {

	public String notificarEntregaDespacho(int numeroVenta);

}
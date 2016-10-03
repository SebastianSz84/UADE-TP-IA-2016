package integracion.interfaces;

import javax.ejb.Remote;

@Remote
public interface SrvEstadoEntrega {

	public String notificarEstadoDespacho(int numeroVenta, String estado);

}
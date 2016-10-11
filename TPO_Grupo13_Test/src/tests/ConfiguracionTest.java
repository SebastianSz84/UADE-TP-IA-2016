package tests;

import org.junit.Test;

import integracion.Comunicacion;

public class ConfiguracionTest {

	@Test
	public void abrirConfiguracion() {
		System.out.println(Comunicacion.getInstancia().getConfiguracion("informarVenta").toString());
		System.out.println("\n");
		System.out.println(Comunicacion.getInstancia().getConfiguracion("logAsync").toString());
		System.out.println("\n");
		System.out.println(Comunicacion.getInstancia().getConfiguracion("logSync").toString());
	}

}
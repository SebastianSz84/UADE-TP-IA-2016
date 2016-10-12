package tests;

import org.junit.Test;

import integracion.Comunicacion;

public class ConfiguracionTest {

	@Test
	public void abrirConfiguracion() {
		System.out.println(Comunicacion.getInstancia().getConfiguracion().toString());
	}

}
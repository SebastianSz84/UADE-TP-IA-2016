package tests;

import integracion.ConfiguracionComunicacion;

public class ConfiguracionTest {

	public static void main(String[] args) {
		System.out.println(ConfiguracionComunicacion.getInstancia().getPropiedad("logSync", "tipo"));
	}

}
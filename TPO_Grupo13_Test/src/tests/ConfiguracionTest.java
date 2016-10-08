package tests;

import integracion.Comunicacion;

public class ConfiguracionTest {

	public static void main(String[] args) {
		System.out.println(Comunicacion.getInstancia().getPropiedad("logSync", "tipo"));
	}

}
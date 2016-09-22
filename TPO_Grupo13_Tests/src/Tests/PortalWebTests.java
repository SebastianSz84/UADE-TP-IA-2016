package Tests;

import org.junit.Assert;
import org.junit.Test;

import Controllers.PortalWeb;
import Dao.UsuarioDAO;
import Dto.ResultadoOperacion;
import Entities.Usuario;

public class PortalWebTests {

	@Test
	public void altaUsuarioOKTest() {

		// Arrange

		// Act
		ResultadoOperacion res = PortalWeb.getInstancia().altaUsuario("usuarioTest", "apellidoTest", "test@mail.com",
				"123456");

		// Assert
		Usuario usuario = UsuarioDAO.get("test@mail.com");

		Assert.assertTrue(res.sosExitoso());
		Assert.assertTrue(usuario != null);
		Assert.assertTrue(usuario.sosUsuario("test@mail.com"));
		Assert.assertTrue(usuario.getVentas() != null && usuario.getVentas().size() == 0);

		UsuarioDAO.deleteEntity(usuario);
	}

	@Test
	public void altaUsuarioSinNombreErrorTest() {

		// Arrange

		// Act
		ResultadoOperacion res = PortalWeb.getInstancia().altaUsuario("", "apellidoTest", "test@mail.com", "123456");

		// Assert
		Assert.assertTrue(!res.sosExitoso());
	}

	@Test
	public void altaUsuarioSinApellidoErrorTest() {

		// Arrange

		// Act
		ResultadoOperacion res = PortalWeb.getInstancia().altaUsuario("nombreTest", "", "test@mail.com", "123456");

		// Assert
		Assert.assertTrue(!res.sosExitoso());
	}

	@Test
	public void altaUsuarioSinUserNameErrorTest() {

		// Arrange

		// Act
		ResultadoOperacion res = PortalWeb.getInstancia().altaUsuario("nombreTest", "apellidoTest", "", "123456");

		// Assert
		Assert.assertTrue(!res.sosExitoso());
	}

	@Test
	public void altaUsuarioSinPasswordErrorTest() {

		// Arrange

		// Act
		ResultadoOperacion res = PortalWeb.getInstancia().altaUsuario("nombreTest", "apellidoTest", "test@mail.com",
				"");

		// Assert
		Assert.assertTrue(!res.sosExitoso());
	}
}

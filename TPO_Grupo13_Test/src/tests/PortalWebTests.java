package tests;

public class PortalWebTests {

	// @Before
	// public void InitializeTestData() {
	// Usuario usuario = UsuarioDAO.get("test@mail.com");
	// if (usuario != null) {
	// UsuarioDAO.deleteEntity(usuario);
	// }
	// }
	//
	// @After
	// public void removetestData() {
	// Usuario usuario = UsuarioDAO.get("test@mail.com");
	// if (usuario != null) {
	// UsuarioDAO.deleteEntity(usuario);
	// }
	// }
	//
	// @Test
	// public void altaUsuarioOKTest() {
	//
	// // Arrange
	//
	// // Act
	// ResultadoOperacion res =
	// PortalWeb.getInstancia().altaUsuario("usuarioTest", "apellidoTest",
	// "test@mail.com",
	// "123456");
	//
	// // Assert
	// Usuario usuario = UsuarioDAO.get("test@mail.com");
	//
	// Assert.assertTrue(res.sosExitoso());
	// Assert.assertTrue(usuario != null);
	// Assert.assertTrue(usuario.sosUsuario("test@mail.com"));
	// Assert.assertTrue(usuario.getVentas() != null &&
	// usuario.getVentas().size() == 0);
	//
	// UsuarioDAO.deleteEntity(usuario);
	// }
	//
	// @Test
	// public void altaUsuarioYaExistenteErrorTest() {
	//
	// // Arrange
	//
	// // Act
	// ResultadoOperacion res =
	// PortalWeb.getInstancia().altaUsuario("usuarioTest", "apellidoTest",
	// "test@mail.com",
	// "123456");
	//
	// res = PortalWeb.getInstancia().altaUsuario("usuarioTest", "apellidoTest",
	// "test@mail.com", "123456");
	//
	// // Assert
	// Assert.assertTrue(!res.sosExitoso());
	//
	// Usuario usuario = UsuarioDAO.get("test@mail.com");
	// UsuarioDAO.deleteEntity(usuario);
	// }
	//
	// @Test
	// public void altaUsuarioSinNombreErrorTest() {
	//
	// // Arrange
	//
	// // Act
	// ResultadoOperacion res = PortalWeb.getInstancia().altaUsuario("",
	// "apellidoTest", "test@mail.com", "123456");
	//
	// // Assert
	// Assert.assertTrue(!res.sosExitoso());
	// }
	//
	// @Test
	// public void altaUsuarioSinApellidoErrorTest() {
	//
	// // Arrange
	//
	// // Act
	// ResultadoOperacion res =
	// PortalWeb.getInstancia().altaUsuario("nombreTest", "", "test@mail.com",
	// "123456");
	//
	// // Assert
	// Assert.assertTrue(!res.sosExitoso());
	// }
	//
	// @Test
	// public void altaUsuarioSinUserNameErrorTest() {
	//
	// // Arrange
	//
	// // Act
	// ResultadoOperacion res =
	// PortalWeb.getInstancia().altaUsuario("nombreTest", "apellidoTest", "",
	// "123456");
	//
	// // Assert
	// Assert.assertTrue(!res.sosExitoso());
	// }
	//
	// @Test
	// public void altaUsuarioSinPasswordErrorTest() {
	//
	// // Arrange
	//
	// // Act
	// ResultadoOperacion res =
	// PortalWeb.getInstancia().altaUsuario("nombreTest", "apellidoTest",
	// "test@mail.com",
	// "");
	//
	// // Assert
	// Assert.assertTrue(!res.sosExitoso());
	// }
	//
	// @Test
	// public void loginUsuarioOKTest() {
	//
	// // Arrange
	// Usuario newUsuario = new Usuario();
	// newUsuario.setNombre("usuarioTest");
	// newUsuario.setApellido("apellidoTest");
	// newUsuario.setUserName("test@mail.com");
	// newUsuario.setPassword("1234567");
	//
	// UsuarioDAO.saveEntity(newUsuario);
	//
	// // Act
	// ResultadoOperacion res =
	// PortalWeb.getInstancia().loginUsuario("test@mail.com", "1234567");
	//
	// // Assert
	// Assert.assertTrue(res.sosExitoso());
	//
	// UsuarioDAO.deleteEntity(newUsuario);
	// }
	//
	// @Test
	// public void loginUsuarioErrorTest() {
	//
	// // Arrange
	// Usuario newUsuario = new Usuario();
	// newUsuario.setNombre("usuarioTest");
	// newUsuario.setApellido("apellidoTest");
	// newUsuario.setUserName("test@mail.com");
	// newUsuario.setPassword("1234567");
	//
	// UsuarioDAO.saveEntity(newUsuario);
	//
	// // Act
	// ResultadoOperacion res =
	// PortalWeb.getInstancia().loginUsuario("test@mail.com", "123456");
	//
	// // Assert
	// Assert.assertTrue(!res.sosExitoso());
	//
	// UsuarioDAO.deleteEntity(newUsuario);
	// }
	//
	// @Test
	// public void listProductosTest() {
	// List<ProductoDTO> lista = PortalWeb.getInstancia().listadoProductos();
	// for (ProductoDTO p : lista) {
	// System.out.println(p.getNombre() + p.getPrecio());
	// }
	// }
}

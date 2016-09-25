package tests;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.CategoriaDAOBean;
import dao.ProductoDAOBean;
import dao.UsuarioDAOBean;
import entities.Categoria;
import entities.ItemVenta;
import entities.Producto;
import entities.Usuario;
import entities.Venta;

public class EntitiesTests {

	@EJB
	private CategoriaDAOBean categoriaDAOBean;
	@EJB
	private UsuarioDAOBean usuarioDAOBean;
	@EJB
	private ProductoDAOBean productoDAOBean;

	private EntitiesTests() {
	}

	@Before
	public void initializeTestData() {
		Categoria categoria = categoriaDAOBean.get("categoriaTest");
		if (categoria == null) {
			categoria = new Categoria();
			categoria.setNombre("categoriaTest");
			categoriaDAOBean.saveEntity(categoria);
		}

		Usuario usuario = usuarioDAOBean.get("test@mail.com");
		if (usuario != null) {
			usuarioDAOBean.deleteEntity(usuario);
		}
	}

	@After
	public void removeTestData() {
		Categoria categoria = categoriaDAOBean.get("categoriaTest");

		if (categoria != null)
			categoriaDAOBean.deleteEntity(categoria);
	}

	@Test
	public void nuevoProductoTest() {

		// Arrange
		Categoria categoria = categoriaDAOBean.get("categoriaTest");

		// Act
		Producto newProducto = new Producto();
		newProducto.setCategoria(categoria);
		newProducto.setCodigo(987123);
		newProducto.setDatosExtra("datosExtraTest");
		newProducto.setDescripcion("descripcionTest");
		newProducto.setMarca("marcaTest");
		newProducto.setNombre("nombreTest");
		newProducto.setOrigen("origenTest");
		newProducto.setPrecio(123.987);
		newProducto.setUrlImagen("#");
		productoDAOBean.saveEntity(newProducto);

		Producto producto = productoDAOBean.get(987123);

		// Assert
		Assert.assertTrue(producto != null);
		Assert.assertTrue(producto.getNombre().equals("nombreTest"));
		Assert.assertTrue(producto.getCategoria().sosCategoria("categoriaTest"));

		productoDAOBean.deleteEntity(producto);
	}

	@Test
	public void nuevoUsuarioTest() {

		// Arrange
		Producto producto = productoDAOBean.get(1857363);

		ItemVenta itemVenta = new ItemVenta();
		itemVenta.setProducto(producto);
		itemVenta.setPrecio(123.543);
		itemVenta.setCantidad(10);

		List<ItemVenta> itemsVenta = new ArrayList<ItemVenta>();
		itemsVenta.add(itemVenta);

		Venta venta = new Venta();
		venta.setItems(itemsVenta);

		// Act
		Usuario newUsuario = new Usuario();
		newUsuario.setNombre("usuarioTest");
		newUsuario.setApellido("apellidoTest");
		newUsuario.setUserName("test@mail.com");
		newUsuario.setPassword("1234567");
		newUsuario.addVenta(venta);

		usuarioDAOBean.saveEntity(newUsuario);

		Usuario usuario = usuarioDAOBean.get("test@mail.com");

		// Assert
		Assert.assertTrue(usuario != null);
		Assert.assertTrue(usuario.sosUsuario("test@mail.com"));
		Assert.assertTrue(usuario.getVentas() != null && usuario.getVentas().size() > 0);
		Assert.assertTrue(usuario.getVentas().get(0).getItems().get(0).getProducto().sosProducto(1857363));

		usuarioDAOBean.deleteEntity(usuario);

	}

	@Test
	public void nuevoCarritoTest() {

		// Arrange
		Producto producto = productoDAOBean.get(1857363);

		// Act
		Usuario newUsuario = new Usuario();
		newUsuario.setNombre("usuarioTest");
		newUsuario.setApellido("apellidoTest");
		newUsuario.setUserName("test@mail.com");
		newUsuario.setPassword("1234567");
		newUsuario.addItemACarrito(producto, 10);

		usuarioDAOBean.saveEntity(newUsuario);

		Usuario usuario = usuarioDAOBean.get("test@mail.com");

		// Assert
		Assert.assertTrue(usuario != null);
		Assert.assertTrue(usuario.sosUsuario("test@mail.com"));
		Assert.assertTrue(usuario.getCarrito() != null && usuario.getCarrito().getItems().size() > 0);
		Assert.assertTrue(usuario.getCarrito().getItems().get(0).getProducto().sosProducto(1857363));

		usuarioDAOBean.deleteEntity(usuario);
	}
}
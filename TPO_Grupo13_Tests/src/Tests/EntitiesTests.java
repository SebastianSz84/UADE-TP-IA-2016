package Tests;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Dao.CategoriaDAO;
import Dao.ProductoDAO;
import Dao.UsuarioDAO;
import Dao.VentaDAO;
import Entities.Categoria;
import Entities.ItemVenta;
import Entities.Producto;
import Entities.Usuario;
import Entities.Venta;

public class EntitiesTests {

	private int idVentaTest;
	
	@Before
	public void initializeTestData() {
		Categoria categoria = CategoriaDAO.get("categoriaTest");
		if (categoria == null) {
			categoria = new Categoria();
			categoria.setNombre("categoriaTest");
			CategoriaDAO.saveEntity(categoria);		
		}
	}

	@Test
	public void nuevoProductoTest() {
		  
		// Arrange
		Categoria categoria = CategoriaDAO.get("categoriaTest");
		
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
		ProductoDAO.saveEntity(newProducto);
		
		Producto producto = ProductoDAO.get(987123);
		  
		// Assert
		Assert.assertTrue(producto != null);
		Assert.assertTrue(producto.getNombre().equals("nombreTest"));
		Assert.assertTrue(producto.getCategoria().sosCategoria("categoriaTest"));
		
		ProductoDAO.deleteEntity(producto);
	}

	@Test
	public void nuevoUsuarioTest() {
		
		// Arrange
		Producto producto = ProductoDAO.get(1857363);
		
		ItemVenta itemVenta = new ItemVenta();
		itemVenta.setProducto(producto);
		itemVenta.setCantidad(10);
		
		List<ItemVenta> itemsVenta = new ArrayList<ItemVenta>();
		itemsVenta.add(itemVenta);
		
		Venta venta = new Venta();
		venta.setEstado("Nueva");
		venta.setFecha(Date.valueOf("20161010"));
		venta.setItems(itemsVenta);
		
		VentaDAO.saveEntity(venta);
		
		// Act
		Usuario newUsuario = new Usuario();
		newUsuario.setNombre("usuarioTest");
		newUsuario.setApellido("apellidoTest");
		newUsuario.setUserName("test@mail.com");
		newUsuario.setPassword("1234567");
		newUsuario.addVenta(venta);
		
		UsuarioDAO.saveEntity(newUsuario);	
		
		Usuario usuario = UsuarioDAO.get("test@mail.com");

		// Assert
		Assert.assertTrue(usuario.sosUsuario("test@mail.com"));
		Assert.assertTrue(usuario.getVentas() != null && usuario.getVentas().size() > 0);
		
		UsuarioDAO.deleteEntity(usuario);
		
	}
	
	@After
	public void removeTestData() {
		Categoria categoria = CategoriaDAO.get("categoriaTest");
		
		if (categoria != null)
			CategoriaDAO.deleteEntity(categoria);		
	}
	  
}
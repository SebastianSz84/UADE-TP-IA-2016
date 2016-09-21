package Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Dao.CategoriaDAO;
import Dao.ProductoDAO;
import Entities.Categoria;
import Entities.Producto;

public class EntitiesTests {
	
	@Before
	public void initializeTestData() {
		Categoria categoria = CategoriaDAO.getCategoria("categoriaTest");
		if (categoria == null) {
			categoria = new Categoria();
			categoria.setNombre("categoriaTest");
			CategoriaDAO.saveEntity(categoria);		
		}
	}

	@Test
	public void nuevoProductoTest() {
		  
		Categoria categoria = CategoriaDAO.getCategoria("categoriaTest");
		  
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
		  
		Assert.assertTrue(producto != null);
		
		Assert.assertTrue(producto.getNombre().equals("nombreTest"));
		
		ProductoDAO.deleteEntity(producto);
	}
	
	@After
	public void removeTestData() {
		Categoria categoria = CategoriaDAO.getCategoria("categoriaTest");
		
		if (categoria != null)
			CategoriaDAO.deleteEntity(categoria);		
	}
	  
}
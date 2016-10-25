USE [UADE-TP-IA-2016]
GO

/*
delete from Ranking
delete from ItemCarrito
delete from Carrito
delete from ItemVenta
delete from Venta
delete from Usuario
delete from Producto
delete from Categoria
*/

SET IDENTITY_INSERT [dbo].[Usuario] ON 

INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (1,  'Leo',  'Esmoris',  'lesmoris@gmail.com',  '123',  '-34.6179003',  '-58.3874423')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (2,  'Seba',  'Szarfsztejn',  'sebastiansz@gmail.com',  '123',  '-34.6197558',  '-58.3829695')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (3,  'Vero',  'Dilernia',  'veritoblack@gmail.com',  '123',  '-38.9460399',  '-68.1277469')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (4,  'Tavo',  'Yauni',  'gustavo.yauny@gmail.com',  '123',  '-38.9472650',  '-68.1269502')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (5,  'Gabriel',  'Cava',  'gabrielalejandrocava@gmail.com',  '123',  '-31.4027010',  '-64.1644477')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (6,  'Usuario',  '6',  'zaaram',  'zaaram',  '-31.4095871',  '-64.1653490')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (7,  'Usuario',  '7',  '123',  '123',  '-32.9585220',  '-60.6677055')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (9,  'Usuario',  '8',  '456',  '456',  '-32.9584500',  '-60.6812667')
SET IDENTITY_INSERT [dbo].[Usuario] OFF

INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [Tipo], [idDeposito]) VALUES (1857363,  'Mesa TV',  'Mesa para TV 42',  'Mesas TV SA', 65.45,  'http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png',  'China',  '', 'Muebles', 'G01')
INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [Tipo], [idDeposito]) VALUES (1858018,  'Coche Paraguas',  '9 meses a 3 años',  'Cochecitos SA', 123.55,  'http://www.como-limpiar.com/wp-content/uploads/2011/06/Carritos_de_bebe_3.jpeg',  'Argentina',  '', 'Niños', 'G01')
INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [Tipo], [idDeposito]) VALUES (1857362,  'Mesa y sillas', 'Mesa para comedor con 4 sillas',  'Mesas SA', 965.45,  'http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png',  'China',  '', 'Muebles', 'G01')
INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [Tipo], [idDeposito]) VALUES (1858013,  'Juguete',  'a partir de 3 años',  'Juguetes SA', 342.55,  'http://www.como-limpiar.com/wp-content/uploads/2011/06/Carritos_de_bebe_3.jpeg',  'Argentina',  '', 'Niños', 'G01')
INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [Tipo], [idDeposito]) VALUES (1857361,  'Heladera',  'Heladera con freezer',  'Heladeras SA', 8365,  'http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png',  'China',  '', 'Electro', 'G01')
INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [Tipo], [idDeposito]) VALUES (1858022,  'Horno',  'Horno electrico',  'Hornos SA', 123.55,  'http://www.como-limpiar.com/wp-content/uploads/2011/06/Carritos_de_bebe_3.jpeg',  'Argentina',  '', 'Electro', 'G01')

SET IDENTITY_INSERT [dbo].[Venta] ON 

INSERT [dbo].[Venta] ([Id], [IdUsuario], [Fecha], [Estado]) VALUES (1, 1, '2016-10-20T00:00:00.000', 'Pendiente')
INSERT [dbo].[Venta] ([Id], [IdUsuario], [Fecha], [Estado]) VALUES (2, 2, '2016-08-20T00:00:00.000', 'Entregado')
SET IDENTITY_INSERT [dbo].[Venta] OFF
SET IDENTITY_INSERT [dbo].[ItemVenta] ON 

INSERT [dbo].[ItemVenta] ([Id], [IdVenta], [CodigoProducto], [Cantidad], [Precio]) VALUES (1, 1, 1858018, 1, 123.55)
INSERT [dbo].[ItemVenta] ([Id], [IdVenta], [CodigoProducto], [Cantidad], [Precio]) VALUES (2, 1, 1857363, 2, 65.45)
INSERT [dbo].[ItemVenta] ([Id], [IdVenta], [CodigoProducto], [Cantidad], [Precio]) VALUES (3, 2, 1858018, 3, 100)
INSERT [dbo].[ItemVenta] ([Id], [IdVenta], [CodigoProducto], [Cantidad], [Precio]) VALUES (4, 2, 1857363, 4, 55.76)
SET IDENTITY_INSERT [dbo].[ItemVenta] OFF


INSERT INTO Ranking VALUES (1857363, 1)
INSERT INTO Ranking VALUES (1858018, 2)

/*
select * from Usuario
select * from Ranking
select * from ItemCarrito
select * from Carrito
select * from Categoria
select * from ItemVenta
select * from Venta
select * from Producto
*/
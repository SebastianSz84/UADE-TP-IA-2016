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

INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (1, N'Leo', N'Esmoris', N'lesmoris@gmail.com', N'123', N'-34.6179003', N'-58.3874423')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (2, N'Seba', N'Szarfsztejn', N'sebastiansz@gmail.com', N'123', N'-34.6197558', N'-58.3829695')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (3, N'Vero', N'Dilernia', N'veritoblack@gmail.com', N'123', N'-38.9460399', N'-68.1277469')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (4, N'Tavo', N'Yauni', N'gustavo.yauny@gmail.com', N'123', N'-38.9472650', N'-68.1269502')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (5, N'Gabriel', N'Cava', N'gabrielalejandrocava@gmail.com', N'123', N'-31.4027010', N'-64.1644477')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (6, N'Usuario', N'6', N'zaaram', N'zaaram', N'-31.4095871', N'-64.1653490')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (7, N'Usuario', N'7', N'123', N'123', N'-32.9585220', N'-60.6677055')
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY]) VALUES (9, N'Usuario', N'8', N'456', N'456', N'-32.9584500', N'-60.6812667')
SET IDENTITY_INSERT [dbo].[Usuario] OFF
SET IDENTITY_INSERT [dbo].[Categoria] ON 

INSERT [dbo].[Categoria] ([Id], [Nombre]) VALUES (1, N'Moda')
INSERT [dbo].[Categoria] ([Id], [Nombre]) VALUES (2, N'Muebles')
INSERT [dbo].[Categoria] ([Id], [Nombre]) VALUES (3, N'Electro')
INSERT [dbo].[Categoria] ([Id], [Nombre]) VALUES (4, N'Niños')
SET IDENTITY_INSERT [dbo].[Categoria] OFF
INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [IdCategoria]) VALUES (1857363, N'Mesa TV', N'Mesa para TV 42', N'Mesas TV SA', 65.45, N'http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png', N'China', N'', 2)
INSERT [dbo].[Producto] ([Codigo], [Nombre], [Descripcion], [Marca], [Precio], [urlImagen], [Origen], [DatosExtra], [IdCategoria]) VALUES (1858018, N'Coche Paraguas', N'9 meses a 3 años', N'Cochecitos SA', 123.55, N'http://www.como-limpiar.com/wp-content/uploads/2011/06/Carritos_de_bebe_3.jpeg', N'Argentina', N'', 4)
SET IDENTITY_INSERT [dbo].[Venta] ON 

INSERT [dbo].[Venta] ([Id], [IdUsuario], [Fecha], [Estado]) VALUES (1, 1, CAST(N'2016-20-10 00:00:00.000' AS DateTime), N'Pendiente')
INSERT [dbo].[Venta] ([Id], [IdUsuario], [Fecha], [Estado]) VALUES (2, 2, CAST(N'2016-20-08 00:00:00.000' AS DateTime), N'Entregado')
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
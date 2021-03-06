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
*/

USE [UADE-TP-IA-2016]
GO
SET IDENTITY_INSERT [dbo].[Usuario] ON 

GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (1, N'Leo', N'Esmoris', N'lesmoris@gmail.com', N'123', N'-34.6179003', N'-58.3874423', 1234567890)
GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (2, N'Seba', N'Szarfsztejn', N'sebastiansz@gmail.com', N'123', N'-34.6197558', N'-58.3829695', 1234567891)
GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (3, N'Vero', N'Dilernia', N'veritoblack@gmail.com', N'123', N'-38.9460399', N'-68.1277469', 1234567892)
GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (4, N'Tavo', N'Yauni', N'gustavo.yauny@gmail.com', N'123', N'-38.9472650', N'-68.1269502', 1234567893)
GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (5, N'Gabriel', N'Cava', N'gabrielalejandrocava@gmail.com', N'123', N'-31.4027010', N'-64.1644477', 1234567894)
GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (6, N'Usuario', N'6', N'zaaram', N'zaaram', N'-31.4095871', N'-64.1653490', 1234567895)
GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (7, N'Usuario', N'7', N'123', N'123', N'-32.9585220', N'-60.6677055', 1234567896)
GO
INSERT [dbo].[Usuario] ([Id], [Nombre], [Apellido], [Username], [Password], [CoordenadasX], [CoordenadasY], [Dni]) VALUES (9, N'Usuario', N'8', N'456', N'456', N'-32.9584500', N'-60.6812667', 1234567897)
GO
SET IDENTITY_INSERT [dbo].[Usuario] OFF
GO
SET IDENTITY_INSERT [dbo].[Producto] ON 

GO
INSERT [dbo].[Producto] ([Id], [Codigo], [Nombre], [Descripcion], [Marca], [Precio], [Foto], [Origen], [DatosExtra], [Tipo], [idDeposito], [FechaAlta]) VALUES (1, 1857361, N'Heladera', N'Heladera con freezer', N'Heladeras SA', 8365, N'http://fravega.vteximg.com.br/arquivos/ids/255130-1000-1000/HELADERA-2-FRIOS-SIGMA-HF24BFS.jpg', N'China', N'', N'Electro', N'G01', CAST(N'2016-10-31 14:00:42.343' AS DateTime))
GO
INSERT [dbo].[Producto] ([Id], [Codigo], [Nombre], [Descripcion], [Marca], [Precio], [Foto], [Origen], [DatosExtra], [Tipo], [idDeposito], [FechaAlta]) VALUES (2, 1857362, N'Mesa y sillas', N'Mesa para comedor con 4 sillas', N'Mesas SA', 965.45, N'http://www.moebelthom.eu/assets/images/california_S_10_mesa_160-215x90__P20_silla.jpg', N'China', N'', N'Muebles', N'G01', CAST(N'2016-10-31 14:00:42.343' AS DateTime))
GO
INSERT [dbo].[Producto] ([Id], [Codigo], [Nombre], [Descripcion], [Marca], [Precio], [Foto], [Origen], [DatosExtra], [Tipo], [idDeposito], [FechaAlta]) VALUES (3, 1857363, N'Mesa TV', N'Mesa para TV 42', N'Mesas TV SA', 65.45, N'http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png', N'China', N'Dato extra Mesa', N'Muebles', N'G01', CAST(N'2016-10-31 14:00:42.340' AS DateTime))
GO
INSERT [dbo].[Producto] ([Id], [Codigo], [Nombre], [Descripcion], [Marca], [Precio], [Foto], [Origen], [DatosExtra], [Tipo], [idDeposito], [FechaAlta]) VALUES (4, 1858013, N'Juguete', N'a partir de 3 años', N'Juguetes SA', 342.55, N'http://cardiffstore.com/wp-content/uploads/2014/04/blog_juguete.jpg', N'Argentina', N'', N'Niños', N'G01', CAST(N'2016-10-31 14:00:42.343' AS DateTime))
GO
INSERT [dbo].[Producto] ([Id], [Codigo], [Nombre], [Descripcion], [Marca], [Precio], [Foto], [Origen], [DatosExtra], [Tipo], [idDeposito], [FechaAlta]) VALUES (5, 1858018, N'Coche Paraguas', N'9 meses a 3 años', N'Cochecitos SA', 123.55, N'http://www.como-limpiar.com/wp-content/uploads/2011/06/Carritos_de_bebe_3.jpeg', N'Argentina', N'', N'Niños', N'G01', CAST(N'2016-10-31 14:00:42.343' AS DateTime))
GO
INSERT [dbo].[Producto] ([Id], [Codigo], [Nombre], [Descripcion], [Marca], [Precio], [Foto], [Origen], [DatosExtra], [Tipo], [idDeposito], [FechaAlta]) VALUES (6, 1858022, N'Horno', N'Horno electrico', N'Hornos SA', 123.55, N'http://www.como-limpiar.com/wp-content/uploads/2011/06/Carritos_de_bebe_3.jpeg', N'Argentina', N'', N'Electro', N'G01', CAST(N'2016-10-31 14:00:42.347' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Producto] OFF
GO
INSERT [dbo].[Ranking] ([IdProducto], [Posicion]) VALUES (1, 1)
GO
INSERT [dbo].[Ranking] ([IdProducto], [Posicion]) VALUES (2, 4)
GO

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
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
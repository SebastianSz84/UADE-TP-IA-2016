USE [UADE-TP-IA-2016]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Venta_Usuario]') AND parent_object_id = OBJECT_ID(N'[dbo].[Venta]'))
ALTER TABLE [dbo].[Venta] DROP CONSTRAINT [FK_Venta_Usuario]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Ranking_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[Ranking]'))
ALTER TABLE [dbo].[Ranking] DROP CONSTRAINT [FK_Ranking_Producto]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ItemVenta_Venta]') AND parent_object_id = OBJECT_ID(N'[dbo].[ItemVenta]'))
ALTER TABLE [dbo].[ItemVenta] DROP CONSTRAINT [FK_ItemVenta_Venta]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ItemVenta_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[ItemVenta]'))
ALTER TABLE [dbo].[ItemVenta] DROP CONSTRAINT [FK_ItemVenta_Producto]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ItemCarrito_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[ItemCarrito]'))
ALTER TABLE [dbo].[ItemCarrito] DROP CONSTRAINT [FK_ItemCarrito_Producto]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ItemCarrito_Carrito]') AND parent_object_id = OBJECT_ID(N'[dbo].[ItemCarrito]'))
ALTER TABLE [dbo].[ItemCarrito] DROP CONSTRAINT [FK_ItemCarrito_Carrito]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Carrito_Usuario]') AND parent_object_id = OBJECT_ID(N'[dbo].[Carrito]'))
ALTER TABLE [dbo].[Carrito] DROP CONSTRAINT [FK_Carrito_Usuario]
GO
IF  EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[DF_Venta_Fecha]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[Venta] DROP CONSTRAINT [DF_Venta_Fecha]
END

GO
/****** Object:  Table [dbo].[Venta]    Script Date: 03/10/2016 21:32:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Venta]') AND type in (N'U'))
DROP TABLE [dbo].[Venta]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 03/10/2016 21:32:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Usuario]') AND type in (N'U'))
DROP TABLE [dbo].[Usuario]
GO
/****** Object:  Table [dbo].[Ranking]    Script Date: 03/10/2016 21:32:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ranking]') AND type in (N'U'))
DROP TABLE [dbo].[Ranking]
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 03/10/2016 21:32:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Producto]') AND type in (N'U'))
DROP TABLE [dbo].[Producto]
GO
/****** Object:  Table [dbo].[ItemVenta]    Script Date: 03/10/2016 21:32:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ItemVenta]') AND type in (N'U'))
DROP TABLE [dbo].[ItemVenta]
GO
/****** Object:  Table [dbo].[ItemCarrito]    Script Date: 03/10/2016 21:32:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ItemCarrito]') AND type in (N'U'))
DROP TABLE [dbo].[ItemCarrito]
GO
/****** Object:  Table [dbo].[Carrito]    Script Date: 03/10/2016 21:32:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Carrito]') AND type in (N'U'))
DROP TABLE [dbo].[Carrito]
GO

/****** Object:  Table [dbo].[Carrito]    Script Date: 10/11/2016 05:06:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carrito](
	[IdUsuario] [int] NOT NULL,
 CONSTRAINT [PK_Carrito] PRIMARY KEY CLUSTERED 
(
	[IdUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ItemCarrito]    Script Date: 10/11/2016 05:06:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ItemCarrito](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdUsuario] [int] NULL,
	[IdProducto] [int] NOT NULL,
	[Cantidad] [int] NOT NULL,
 CONSTRAINT [PK_ItemCarrito] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ItemVenta]    Script Date: 10/11/2016 05:06:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ItemVenta](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdVenta] [int] NULL,
	[IdProducto] [int] NOT NULL,
	[Cantidad] [int] NOT NULL,
	[Precio] [float] NOT NULL,
 CONSTRAINT [PK_ItemVenta] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Producto]    Script Date: 10/11/2016 05:06:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Producto](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Codigo] [int] NOT NULL,
	[Nombre] [nvarchar](200) NOT NULL,
	[Descripcion] [nvarchar](200) NOT NULL,
	[Marca] [nvarchar](50) NOT NULL,
	[Precio] [float] NOT NULL,
	[Foto] [nvarchar](4000) NOT NULL,
	[Origen] [nvarchar](4000) NOT NULL,
	[DatosExtra] [nvarchar](4000) NOT NULL,
	[Tipo] [varchar](10) NOT NULL,
	[idDeposito] [varchar](5) NOT NULL,
	[FechaAlta] [datetime] NOT NULL CONSTRAINT [DF_Producto_FechaAlta]  DEFAULT (getdate()),
 CONSTRAINT [PK_Producto] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ranking]    Script Date: 10/11/2016 05:06:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ranking](
	[IdProducto] [int] NOT NULL,
	[Posicion] [int] NOT NULL,
 CONSTRAINT [PK_Ranking] PRIMARY KEY CLUSTERED 
(
	[IdProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 10/11/2016 05:06:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Usuario](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[Apellido] [varchar](100) NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[CoordenadasX] [varchar](50) NOT NULL,
	[CoordenadasY] [varchar](50) NOT NULL,
	[Dni] [int] NOT NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Dni] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Venta]    Script Date: 10/11/2016 05:06:36 p.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Venta](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IdUsuario] [int] NULL,
	[Fecha] [datetime] NOT NULL,
	[Estado] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Venta] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Venta] ADD  CONSTRAINT [DF_Venta_Fecha]  DEFAULT (getdate()) FOR [Fecha]
GO
ALTER TABLE [dbo].[Carrito]  WITH CHECK ADD  CONSTRAINT [FK_Carrito_Usuario] FOREIGN KEY([IdUsuario])
REFERENCES [dbo].[Usuario] ([Id])
GO
ALTER TABLE [dbo].[Carrito] CHECK CONSTRAINT [FK_Carrito_Usuario]
GO
ALTER TABLE [dbo].[ItemCarrito]  WITH CHECK ADD  CONSTRAINT [FK_ItemCarrito_Carrito] FOREIGN KEY([IdUsuario])
REFERENCES [dbo].[Carrito] ([IdUsuario])
GO
ALTER TABLE [dbo].[ItemCarrito] CHECK CONSTRAINT [FK_ItemCarrito_Carrito]
GO
ALTER TABLE [dbo].[ItemCarrito]  WITH CHECK ADD  CONSTRAINT [FK_ItemCarrito_Producto] FOREIGN KEY([IdProducto])
REFERENCES [dbo].[Producto] ([Id])
GO
ALTER TABLE [dbo].[ItemCarrito] CHECK CONSTRAINT [FK_ItemCarrito_Producto]
GO
ALTER TABLE [dbo].[ItemVenta]  WITH CHECK ADD  CONSTRAINT [FK_ItemVenta_Producto] FOREIGN KEY([IdProducto])
REFERENCES [dbo].[Producto] ([Id])
GO
ALTER TABLE [dbo].[ItemVenta] CHECK CONSTRAINT [FK_ItemVenta_Producto]
GO
ALTER TABLE [dbo].[ItemVenta]  WITH CHECK ADD  CONSTRAINT [FK_ItemVenta_Venta] FOREIGN KEY([IdVenta])
REFERENCES [dbo].[Venta] ([Id])
GO
ALTER TABLE [dbo].[ItemVenta] CHECK CONSTRAINT [FK_ItemVenta_Venta]
GO
ALTER TABLE [dbo].[Ranking]  WITH CHECK ADD  CONSTRAINT [FK_Ranking_Producto] FOREIGN KEY([IdProducto])
REFERENCES [dbo].[Producto] ([Id])
GO
ALTER TABLE [dbo].[Ranking] CHECK CONSTRAINT [FK_Ranking_Producto]
GO
ALTER TABLE [dbo].[Venta]  WITH CHECK ADD  CONSTRAINT [FK_Venta_Usuario] FOREIGN KEY([IdUsuario])
REFERENCES [dbo].[Usuario] ([Id])
GO
ALTER TABLE [dbo].[Venta] CHECK CONSTRAINT [FK_Venta_Usuario]
GO

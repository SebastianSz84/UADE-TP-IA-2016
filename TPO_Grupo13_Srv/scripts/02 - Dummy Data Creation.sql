/*
delete from Usuario
delete from Producto
delete from Categoria
delete from Venta
delete from ItemVenta
*/

SET IDENTITY_INSERT Usuario ON

insert into Usuario (Id, Nombre, Apellido, Username, Password) values (1, 'Leo', 'Esmoris', 'lesmoris@gmail.com', '123')
insert into Usuario (Id, Nombre, Apellido, Username, Password) values (2, 'Seba', 'Szarfsztejn', 'sebastiansz@gmail.com', '123')
insert into Usuario (Id, Nombre, Apellido, Username, Password) values (3, 'Vero', 'Dilernia', 'veritoblack@gmail.com', '123')
insert into Usuario (Id, Nombre, Apellido, Username, Password) values (4, 'Tavo', 'Yauni', 'gustavo.yauny@gmail.com', '123')
insert into Usuario (Id, Nombre, Apellido, Username, Password) values (5, 'Gabriel', 'Cava', 'gabrielalejandrocava@gmail.com', '123')

SET IDENTITY_INSERT Usuario OFF

SET IDENTITY_INSERT Categoria ON

insert into Categoria (Id, Nombre) values (1, 'Moda')
insert into Categoria (Id, Nombre) values (2, 'Muebles')
insert into Categoria (Id, Nombre) values (3, 'Electro')
insert into Categoria (Id, Nombre) values (4, 'Niños')
go

SET IDENTITY_INSERT Categoria OFF

insert into Producto values (1858018, 'Coche Paraguas', '9 meses a 3 años', 'Cochecitos SA', 123.55, 'http://www.como-limpiar.com/wp-content/uploads/2011/06/Carritos_de_bebe_3.jpeg', 'Argentina', '', 4)
insert into Producto values (1857363, 'Mesa TV', 'Mesa para TV 42', 'Mesas TV SA', 65.45, 'http://novogar.com.ar/media/catalog/product/cache/1/image/736x460/9df78eab33525d08d6e5fb8d27136e95/m/u/muebles-desarmable-mesa-lcd-tv8000-r08_02-melamina-hasta-42-pulgadas-novogar.png', 'China', '', 2)
go

SET IDENTITY_INSERT Venta ON

insert into Venta (Id, IdUsuario, Fecha, Estado) values (1, 1, '20161020', 'Pendiente')
insert into Venta (Id, IdUsuario, Fecha, Estado) values (2, 2, '20160820', 'Entregado')
go

SET IDENTITY_INSERT Venta OFF

SET IDENTITY_INSERT ItemVenta ON

insert into ItemVenta (Id, IdVenta, CodigoProducto, Cantidad, Precio) values (1, 1, 1858018, 1, 123.55)
insert into ItemVenta (Id, IdVenta, CodigoProducto, Cantidad, Precio) values (2, 1, 1857363, 2, 65.45)
insert into ItemVenta (Id, IdVenta, CodigoProducto, Cantidad, Precio) values (3, 2, 1858018, 3, 100)
insert into ItemVenta (Id, IdVenta, CodigoProducto, Cantidad, Precio) values (4, 2, 1857363, 4, 55.76)
go

SET IDENTITY_INSERT ItemVenta OFF

/*
select * from Usuario
select * from Categoria
select * from Producto
*/
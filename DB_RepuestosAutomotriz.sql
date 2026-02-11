Drop database if exists DBRepuestosAutomotriz_in5cm;
create database DBRepuestosAutomotriz_in5cm;
use DBRepuestosAutomotriz_in5cm;

create table Proveedores(
 id_proveedor int auto_increment not null,
 nombre_proveedor varchar(60) not null,
 telefono_proveedor int not null,
 direccion varchar(100) not null,
 email_proveedor varchar(100) not null,
 primary key PK_id_proveedor(id_proveedor)
);

create table Empleados(
 id_empleado int auto_increment not null,
 nombre_empleado varchar(60) not null,
 apellido_empleado varchar(60) not null,
 puesto_empleado varchar(20) null,
 email_empleado varchar(100) not null,
 primary key PK_id_empleado(id_empleado)
);

create table Repuestos(
 id_repuesto int auto_increment not null,
 nombre_repuesto varchar(60) not null,
 categoria_repuesto varchar(60) not null,
 precio_compra double not null,
 precio_venta double not null,
 id_proveedor int not null,
 primary key PK_id_repuesto(id_repuesto),
 constraint FK_repuesto_proveedor foreign key (id_proveedor)
references proveedores(id_proveedor) on delete cascade
);

create table Ventas(
 id_venta int auto_increment not null,
 fecha_venta date not null,
 cantidad int not null,
 total double not null,
 id_empleado int not null,
 id_repuesto int not null,
 primary key PK_id_venta(id_venta),
 constraint FK_ventas_empleado foreign key (id_empleado)
references Empleados(id_empleado) on delete cascade,
 constraint FK_ventas_repuestos foreign key (id_repuesto)
references Repuestos(id_repuesto) on delete cascade
);


-- PROCEDIMIENTOS ALMACENADOS -- 

	-- PROVEEDORES --
-- Create --
Delimiter $$ 
	create procedure sp_proveedores_create(p_nombre_proveedor varchar(60), p_telefono_proveedor int, 
    p_direccion varchar(100), p_email_proveedor varchar(100))
    begin 
		insert into Proveedores(nombre_proveedor, telefono_proveedor, direccion, email_proveedor)
		values (p_nombre_proveedor, p_telefono_proveedor, p_direccion, p_email_proveedor);
		select last_insert_id() as id_proveedor;
    end $$
Delimiter ;

-- Delete --
Delimiter $$
	create procedure sp_porveedores_delete(in p_id_proveedor int )
    begin
		delete from Proveedores where id_proveedor = p_id_proveedor;
        select row_count() as filas_afectadas;
    end $$
Delimiter ;

-- Read -- 
Delimiter $$
	create procedure sp_proveedores_read_all()
    begin 
		select * from Proveedores order by id_proveedor;
    end $$
Delimiter ;

-- Update -- 
Delimiter $$
	create procedure sp_proveedores_update(in p_id_proveedor int, in p_nombre_proveedor varchar(60), in p_telefono_proveedor int, 
    in p_direccion varchar(100), in p_email_proveedor varchar(100))
    begin 
		update Proveedores 
		set id_proveedor = p_id_proveedor,
			nombre_proveedor = p_nombre_proveedor,
            telefono_proveedor = p_telefono_proveedor,
            direccion = p_direccion,
            email_proveedor = p_email_proveedor
            where id_proveedor = p_id_proveedor;
		select row_count() as filas_afectadas;
    end $$
Delimiter ;

	-- EMPLEADOS --
-- Create --
Delimiter $$
	create procedure sp_empleados_create (e_nombre_empleado varchar(60), e_apellido_empleado varchar(60), e_puesto_empleado varchar(20),
    e_email_empleado varchar(100))
    begin 
		insert into Empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
		values (e_nombre_empleado, e_apellido_empleado, e_puesto_empleado, e_email_empleado);
		select last_insert_id() as id_empleado;
	end $$
Delimiter ;

-- Delete --
Delimiter $$
	create procedure sp_empleados_delete(in e_id_empleados int)
    begin 
		delete from Empleados where id_empleado = e_id_empleados;
        select row_count() as filas_afectadas;
    end $$
Delimiter ;

-- Read -- 
Delimiter $$ 
	create procedure sp_empleados_read_all ()
    begin 
		select * from Empleados order by id_empleado;
    end $$
Delimiter ;

-- Update -- 	
Delimiter $$
	create procedure sp_empleados_update(in e_id_empleado int, in e_nombre_empleado varchar(60), in e_apellido_empleado varchar(60),
    in e_puesto_empleado varchar(20), in e_email_empleado varchar(100))
    begin
		update Empleados
        set id_empleado = e_id_empleado,
			nombre_empleado = e_nombre_empleado,
            apellido_empleado = e_apellido_empleado,
            puesto_empleado = e_puesto_empleado,
            email_empleado = e_email_empleado
            where id_empleado = e_id_empleado;
		select row_count() as filas_afectadas;
    end $$
Delimiter ;

	-- RESPUESTOS -- 
-- Create -- 
Delimiter $$
	create procedure sp_repuestos_create(r_nombre_repuesto varchar(60),  r_categoria_repuesto varchar(60), r_precio_compra double,
    r_precio_venta double, r_id_proveedor int)
    begin
		insert into Repuestos(nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor) 
		values (r_nombre_repuesto, r_categoria_repuesto, r_precio_compra, r_precio_venta, r_id_proveedor);
	select last_insert_id() as id_repuesto;
    end $$
Delimiter ;

-- Delete --
Delimiter $$
	create procedure sp_repuestos_delete(in r_idrps int )
    begin
		delete from Repuestos where id_repuesto = r_idrps;
        select row_count() as filas_afectadas;
    end $$
Delimiter ;

-- Read -- 
Delimiter $$
	create procedure sp_repuestos_read_all()
    begin 
		select * from Repuestos order by id_repuesto;
    end $$
Delimiter ;

-- Update -- 
Delimiter $$
	create procedure sp_repuestos_update(in r_id_repuesto int, in r_nombre_repuesto varchar(60), in r_categoria_repuesto varchar(60), 
    in r_precio_compra double, in r_precio_venta double, in r_id_proveedor int)
    begin
		update Repuestos 
        set id_repuesto = r_id_repuesto,
			nombre_repuesto = r_nombre_repuesto,
            categoria_repuesto = r_categoria_repuesto,
            precio_compra = r_precio_compra,
            precio_venta = r_precio_venta,
            id_proveedor = r_id_proveedor
			where id_repuesto = r_id_repuesto; 
		select row_count() as filas_afectadas;
	end $$
Delimiter ;

-- VENTAS -- 
-- Create -- 
Delimiter $$
	create procedure sp_ventas_create (v_fecha_venta date, v_cantidad int, v_total double, v_id_empleado int, v_id_repuesto int)
    begin
		insert into Ventas (fecha_venta, cantidad, total, id_empleado, id_repuesto) 
        values (v_fecha_venta, v_cantidad, v_total, v_id_empleado, v_id_repuesto);
	select last_insert_id() as id_venta; 
    end $$
Delimiter ;

-- Delete -- 
Delimiter $$
	create procedure sp_ventas_delete(in v_id_venta int )
	begin 
		delete from Ventas where id_venta = v_id_venta;
        select row_count() as filas_afectadas;
    end $$
Delimiter ;

-- Read -- 
Delimiter $$
	create procedure sp_ventas_read_all()
    begin 
		select * from Ventas order by id_venta;
    end $$
Delimiter ;

-- Update -- 
Delimiter $$
	create procedure sp_ventas_update(in v_id_venta int, in v_fecha_venta date, in v_cantidad int, in v_total double, 
		in v_id_empleado int, in v_id_repuesto int)
    begin
		update Ventas 
			set id_venta = v_id_venta,
				fecha_venta = v_fecha_venta,
                cantidad = v_cantidad,
                total = v_total,
                id_empleado = v_id_empleado,
                id_repuesto = v_id_repuesto
			where id_venta = v_id_venta;
		select row_count() as filas_afectadas; 
    end $$
Delimiter ;

call sp_proveedores_create('Distribuidora López', 80955512, 'Av. Independencia 123, Santo Domingo', 'distribuidoralp@gmail.com');
call sp_proveedores_create('Suministros García', 80955523, 'Calle Duarte 45, Santiago', 'suministrosgarcia@yahoo.com');
call sp_proveedores_create('Comercial Martínez', 80955534, 'Av. Las Américas 789, Santo Domingo Este', 'comercialmartinez@hotmail.com');
call sp_proveedores_create('Importadora Rodríguez', 80955545, 'Calle Mella 210, La Vega', 'importadorarodriguez@outlook.com');
call sp_proveedores_create('Proveedora Hernández', 80955556, 'Av. Constitución 56, San Cristóbal', 'proveedorahernandez@gmail.com');
call sp_proveedores_create('Distribuciones Santana', 80955567, 'Calle Colón 88, Puerto Plata', 'distribucionessantana@yahoo.com');
call sp_proveedores_create('Almacenes Jiménez', 80955578, 'Av. Circunvalación 300, Santiago', 'almacenesjimenez@hotmail.com');
call sp_proveedores_create('Servicios Globales SRL', 80955589, 'Calle Principal 12, Higüey', 'serviciosglobales@outlook.com');
call sp_proveedores_create('Comercial Dominicana', 80955590, 'Av. Central 145, Baní', 'comercialdominicana@gmail.com');
call sp_proveedores_create('Distribuidora Nacional', 80955501, 'Calle Comercio 67, San Pedro de Macorís', 'distribuidoranacional@yahoo.com');

call sp_empleados_create('Carlos', 'Pérez', 'Administrador', 'carlosperez@gmail.com');
call sp_empleados_create('María', 'Gómez', 'Contador', 'mariagomez@yahoo.com');
call sp_empleados_create('Luis', 'Rodríguez', 'Vendedor', 'luisrodriguez@hotmail.com');
call sp_empleados_create('Ana', 'Martínez', 'Supervisor', 'anamartinez@outlook.com');
call sp_empleados_create('José', 'Hernández', 'Gerente', 'josehernandez@gmail.com');
call sp_empleados_create('Laura', 'Ramírez', 'Asistente', 'lauraramirez@yahoo.com');
call sp_empleados_create('Miguel', 'Torres', 'Técnico', 'migueltorres@hotmail.com');
call sp_empleados_create('Sofía', 'Castillo', 'Recepcionista', 'sofiacastillo@outlook.com');
call sp_empleados_create('Pedro', 'Morales', 'Almacén', 'pedromorales@gmail.com');
call sp_empleados_create('Elena', 'Vargas', 'RecursosHumanos', 'elenavargas@yahoo.com');

call sp_repuestos_create('Filtro de Aceite', 'Motor', 250.00, 400.00, 1);
call sp_repuestos_create('Pastillas de Freno', 'Frenos', 800.00, 1200.00, 2);
call sp_repuestos_create('Batería 12V', 'Eléctrico', 3500.00, 4500.00, 3);
call sp_repuestos_create('Amortiguador Delantero', 'Suspensión', 1800.00, 2500.00, 4);
call sp_repuestos_create('Radiador', 'Refrigeración', 4200.00, 5500.00, 5);
call sp_repuestos_create('Bujías', 'Motor', 150.00, 300.00, 6);
call sp_repuestos_create('Alternador', 'Eléctrico', 5000.00, 6500.00, 7);
call sp_repuestos_create('Disco de Freno', 'Frenos', 1200.00, 1800.00, 8);
call sp_repuestos_create('Bomba de Agua', 'Refrigeración', 2200.00, 3200.00, 9);
call sp_repuestos_create('Filtro de Aire', 'Motor', 300.00, 500.00, 10);

call sp_ventas_create('2026-01-05', 3, 450.00, 1, 2);
call sp_ventas_create('2026-01-06', 1, 150.00, 2, 3);
call sp_ventas_create('2026-01-07', 5, 1250.50, 3, 1);
call sp_ventas_create('2026-01-08', 2, 300.00, 1, 4);
call sp_ventas_create('2026-01-09', 4, 800.75, 4, 2);
call sp_ventas_create('2026-01-10', 6, 2100.00, 2, 5);
call sp_ventas_create('2026-01-11', 2, 520.40, 3, 3);
call sp_ventas_create('2026-01-12', 7, 3150.00, 5, 1);
call sp_ventas_create('2026-01-13', 1, 95.99, 4, 6);
call sp_ventas_create('2026-01-14', 3, 675.30, 1, 7);



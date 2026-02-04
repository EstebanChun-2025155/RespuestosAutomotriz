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

Insert into Proveedores (nombre_proveedor, telefono_proveedor, direccion, email_proveedor) values
('Proveedor Alfa S.A.', 22334411, 'Zona 1, Ciudad de Guatemala', 'contacto@proveedoralfa.com'),
('Repuestos Centro', 22998877, 'Calzada Roosevelt 10-55, Zona 7', 'ventas@repuestoscentro.com'),
('MotoPartes GT', 24001122, 'Avenida Petapa 23-10, Zona 12', 'info@motopartesgt.com'),
('AutoSuministros Maya', 23335566, 'Boulevard Los Próceres 5-20, Zona 10', 'servicio@autosuministrosmaya.com'),
('Distribuidora El Tornillo', 24667788, 'Ruta al Atlántico Km 6.5, Zona 17', 'pedidos@eltornillo.com'),
('Partes y Más', 22112233, '6a Avenida 12-45, Zona 9', 'atencion@partesymas.com'),
('Súper Repuestos', 24889900, 'Anillo Periférico 18-90, Zona 11', 'super@superrepuestos.com'),
('LubriMotor Supply', 22776655, 'Carretera a El Salvador Km 14, Fraijanes', 'ventas@lubrimotor.com'),
('ElectroAuto Pro', 23114455, 'Zona 4, Mixco, Guatemala', 'soporte@electroautopro.com'),
('Importadora La Bodega', 23990011, 'Zona 3, Villa Nueva, Guatemala', 'importaciones@labodega.com');

Insert into Empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado) values
('Carlos', 'Méndez', 'Vendedor', 'carlos.mendez@empresa.com'),
('Ana', 'López', 'Cajero', 'ana.lopez@empresa.com'),
('Jorge', 'Pérez', 'Vendedor', 'jorge.perez@empresa.com'),
('María', 'García', 'Supervisor', 'maria.garcia@empresa.com'),
('Luis', 'Ramírez', 'Bodega', 'luis.ramirez@empresa.com'),
('Sofía', 'Castillo', 'Vendedor', 'sofia.castillo@empresa.com'),
('Diego', 'Hernández', 'Cajero', 'diego.hernandez@empresa.com'),
('Paola', 'Morales', 'Bodega', 'paola.morales@empresa.com'),
('Ricardo', 'Vásquez', 'Supervisor', 'ricardo.vasquez@empresa.com'),
('Elena', 'Chávez', 'Vendedor', 'elena.chavez@empresa.com');

Insert into Repuestos (nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor) values
('Filtro de aceite', 'Filtros', 25.00, 45.00, 1),
('Bujía estándar', 'Encendido', 12.00, 22.00, 2),
('Pastillas de freno delanteras', 'Frenos', 85.00, 130.00, 3),
('Correa de tiempo', 'Motor', 120.00, 180.00, 4),
('Amortiguador delantero', 'Suspensión', 210.00, 320.00, 5),
('Batería 12V 60Ah', 'Eléctrico', 350.00, 480.00, 6),
('Aceite 10W-30 (1L)', 'Lubricantes', 35.00, 55.00, 7),
('Bombilla H4', 'Iluminación', 18.00, 30.00, 8),
('Sensor O2', 'Sensores', 260.00, 390.00, 9),
('Radiador compacto', 'Enfriamiento', 420.00, 600.00, 10);

Insert into Ventas (fecha_venta, cantidad, total, id_empleado, id_repuesto) values
('2026-01-10', 2, 90.00, 1, 1),   
('2026-01-12', 4, 88.00, 2, 2),   
('2026-01-15', 1, 130.00, 3, 3),  
('2026-01-18', 1, 180.00, 4, 4),  
('2026-01-20', 2, 640.00, 5, 5),  
('2026-01-22', 1, 480.00, 6, 6),  
('2026-01-25', 6, 330.00, 7, 7),  
('2026-01-27', 3, 90.00, 8, 8),   
('2026-01-30', 1, 390.00, 9, 9),  
('2026-02-02', 1, 600.00, 10, 10);


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



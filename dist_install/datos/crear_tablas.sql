
create database if not exists proyecto;
use proyecto;
DROP table if exists usuario;
DROP table if exists persona ;
DROP TABLE if exists factura;
DROP TABLE if exists producto;
DROP TABLE if exists nota;
DROP TABLE if exists negocio;

CREATE TABLE usuario (
    pass varchar(500) NOT NULL
);


CREATE TABLE persona (
    ID_PERSONA        integer NOT NULL PRIMARY KEY auto_increment,
    nombre   VARCHAR(15),
    apellido      VARCHAR(30),
	direccion  VARCHAR(50),
	telefono integer,
	email VARCHAR(50),
	type VARCHAR(20)
);


   
CREATE TABLE producto(
	ID_producto     integer NOT NULL PRIMARY KEY auto_increment,	
	nombre VARCHAR(20) NOT NULL, 
	precio decimal(10,2) NOT NULL, 
	peso decimal(10,3) NOT NULL,
    cantidad integer NOT NULL
);


CREATE TABLE factura (
	ID_factura   integer NOT NULL PRIMARY KEY auto_increment,	
    fecha Date NOT NULL,
    trabajo VARCHAR(500) NOT NULL,
	precio decimal(10,2) NOT NULL
);

CREATE TABLE nota (
	ID_nota integer NOT NULL PRIMARY KEY auto_increment,
	fecha Date NOT NULL,
	detalle VARCHAR(500) NOT NULL,
	debe decimal(10,2) NOT NULL,
	haber decimal(10,2) NOT NULL
);

CREATE TABLE  negocio (
	ID_factura integer NOT NULL,
	ID_persona integer NOT NULL,
	ID_producto integer NOT NULL,
    cantidad integer NOT NULL

);




commit;
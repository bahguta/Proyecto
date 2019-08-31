
DROP table persona cascade constraint;
DROP TABLE factura cascade constraint;
DROP TABLE producto cascade constraint;
DROP TABLE nota cascade constraint;
DROP TABLE negocio cascade constraint;



CREATE TABLE persona (
    ID_PERSONA        NUMBER NOT NULL,
    nombre   VARCHAR2(15),
    apellido      VARCHAR2(30),
	direccion  VARCHAR2(50),
	telefono number,
	email VARCHAR2(50),
	type VARCHAR2(20)
);


   
CREATE TABLE producto(
	ID_producto       NUMBER NOT NULL,	
	nombre VARCHAR2(20), 
	precio NUMBER(10,2), 
	peso NUMBER(10,3),
    cantidad NUMBER
);


CREATE TABLE factura (
	ID_factura        NUMBER NOT NULL,	
    fecha Date,
    trabajo VARCHAR2(500),
	precio NUMBER(10,2)
);

commit;
CREATE TABLE nota (
	ID_nota NUMBER NOT NULL,
	fecha Date,
	detalle VARCHAR2(500),
	debe NUMBER(10,2),
	haber NUMBER(10,2)
);

CREATE TABLE  negocio (
	ID_factura NUMBER NOT NULL,
	ID_persona NUMBER NOT NULL,
	ID_producto NUMBER NOT NULL,
    cantidad NUMBER
);



drop sequence ID_persona_seq;
drop sequence ID_producto_seq;
drop sequence ID_factura_seq;
drop sequence ID_nota_seq;



create sequence ID_persona_seq
	start with 1 
	maxvalue 10000
	INCREMENT by 1;
	
create sequence ID_producto_seq
	start with 1 
	maxvalue 10000
	INCREMENT by 1; 

create sequence ID_factura_seq
	start with 1 
	maxvalue 10000
	INCREMENT by 1; 
	
create sequence ID_nota_seq
	start with 1 
	maxvalue 10000
	INCREMENT by 1; 

	
	
	
	
ALTER TABLE persona ADD CONSTRAINT pk_id_persona PRIMARY KEY ( ID_persona);	
ALTER TABLE producto ADD CONSTRAINT pk_id_producto PRIMARY KEY ( ID_producto);
ALTER TABLE factura ADD CONSTRAINT pk_id_factura PRIMARY KEY ( ID_factura);
ALTER TABLE nota ADD CONSTRAINT pk_id_nota PRIMARY KEY ( ID_nota);




ALTER TABLE negocio ADD CONSTRAINT fk_id_persona FOREIGN key (ID_persona) REFERENCES persona (ID_persona)
	ON DELETE CASCADE;
ALTER TABLE negocio ADD CONSTRAINT fk_id_producto FOREIGN key (ID_producto) REFERENCES producto (ID_producto)
	ON DELETE CASCADE;
ALTER TABLE negocio ADD CONSTRAINT fk_id_factura FOREIGN key (ID_factura) REFERENCES factura (ID_factura)
	ON DELETE CASCADE;



commit;
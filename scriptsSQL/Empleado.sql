-- -*- mode: SQL -*-
-- Empleado.sql - Script con datos de un empleado

-- Datos Persona
INSERT
	INTO persona (persona_primernom, persona_segundonom, persona_primerapell, persona_segundoapell, persona_direccion)
	VALUES ('JOEL', 'GODOY', 'DEL', 'POZO', 'Glorieta Mayor, 97, 03234, Benimassot');	
-- Datos DNI
INSERT
	INTO dni (dni_tipodoc, dni_numident)
	VALUES ('Cedula de ciudadanía', '33393370');
-- Datos genero
INSERT
	INTO genero (genero_sexo)
	VALUES ('Masculino');
-- Datos estado civil
INSERT
	INTO estadocivil (estadocivil_tipo)
	VALUES ('Casado');
-- Datos Ciudad-Persona
INSERT
	INTO ciudad (ciudad_nombre)
	VALUES ('Guichon');	
-- Datos contacto
INSERT
	INTO contacto (contacto_tipotel, contacto_numtel, contacto_email)
	VALUES ('Celular', '615205460', 'yng71qnx7@netscape.com');
-- Datos ciudad-Empresa
INSERT
	INTO ciudad (ciudad_nombre)
	VALUES ('Bountiful');
-- Datos Empresa
INSERT
	INTO empresa (empresa_nomempr, empresa_identtrib, empresa_fk_ciudad)
	VALUES ('Fefef Cargo', '1680865841-3', 2);
-- Datos Empleado
INSERT
	INTO empleado (empleado_datanac, empleado_fk_persona, empleado_fk_dni, empleado_fk_genero, empleado_fk_estadocivil, empleado_fk_ciudad, empleado_fk_empresa, empleado_fk_contacto)
	VALUES ('1988-07-06', 1, 1, 1, 1, 1, 1, 1);

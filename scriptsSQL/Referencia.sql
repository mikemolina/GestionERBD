-- -*- mode: SQL -*-
-- Referencia.sql - Script con datos de una Referencia

-- Datos Persona
INSERT
	INTO persona (persona_primernom, persona_segundonom, persona_primerapell, persona_segundoapell, persona_direccion)
	VALUES ('BARNIE', 'JACK', 'BUENO', 'NORIEGA', 'Rua Antônio José da Silva, 1060');	
-- Datos Ciudad-Persona
INSERT
	INTO ciudad (ciudad_nombre)
	VALUES ('Paranavaí');	
-- Datos contacto
INSERT
	INTO contacto (contacto_tipotel, contacto_numtel, contacto_email)
	VALUES ('Fax', '5936-4744', 'barjack@rhyta.com');
-- Datos Tipo Referencia
INSERT
	INTO tiporef (tiporef_tiporef)
	VALUES ('Laboral');
-- Datos Referencia
INSERT
	INTO referencia (referencia_fk_persona, referencia_fk_ciudad, referencia_fk_contacto, referencia_fk_tiporef)
	VALUES (2, 3, 2, 1);

--- -*- mode: SQL -*-
--- crearTBL.sql - Script SQL para crear base de datos y generar tablas

---
--- Base de datos
---
CREATE DATABASE
IF NOT EXISTS GestionERBD;
USE GestionERBD;
---
--- Tabla ciudad
---
CREATE TABLE `ciudad` (
  `ciudad_id` int(11) NOT NULL AUTO_INCREMENT,
  `ciudad_nombre` varchar(15) NOT NULL,
  PRIMARY KEY (`ciudad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla contacto
---
CREATE TABLE `contacto` (
  `contacto_id` int(11) NOT NULL AUTO_INCREMENT,
  `contacto_tipotel` varchar(10) NOT NULL,
  `contacto_numtel` varchar(15) NOT NULL,
  `contacto_email` varchar(50) NOT NULL,
  PRIMARY KEY (`contacto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla dni
---
CREATE TABLE `dni` (
  `dni_id` int(11) NOT NULL AUTO_INCREMENT,
  `dni_tipodoc` varchar(25) NOT NULL,
  `dni_numident` varchar(15) NOT NULL,
  PRIMARY KEY (`dni_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla empleado
---
CREATE TABLE `empleado` (
  `empleado_id` int(11) NOT NULL AUTO_INCREMENT,
  `empleado_datanac` date NOT NULL,
  `empleado_fk_persona` int(11) NOT NULL,
  `empleado_fk_dni` int(11) NOT NULL,
  `empleado_fk_genero` int(11) NOT NULL,
  `empleado_fk_estadocivil` int(11) NOT NULL,
  `empleado_fk_ciudad` int(11) NOT NULL,
  `empleado_fk_empresa` int(11) NOT NULL,
  `empleado_fk_contacto` int(11) NOT NULL,
  PRIMARY KEY (`empleado_id`),
  FOREIGN KEY (`empleado_fk_persona`) REFERENCES `persona` (`persona_id`),
  FOREIGN KEY (`empleado_fk_dni`) REFERENCES `dni` (`dni_id`),
  FOREIGN KEY (`empleado_fk_genero`) REFERENCES `genero` (`genero_id`),
  FOREIGN KEY (`empleado_fk_estadocivil`) REFERENCES `estadocivil` (`estadocivil_id`),
  FOREIGN KEY (`empleado_fk_ciudad`) REFERENCES `ciudad` (`ciudad_id`),
  FOREIGN KEY (`empleado_fk_empresa`) REFERENCES `empresa` (`empresa_id`),
  FOREIGN KEY (`empleado_fk_contacto`) REFERENCES `contacto` (`contacto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla empresa
---
CREATE TABLE `empresa` (
  `empresa_id` int(11) NOT NULL AUTO_INCREMENT,
  `empresa_nomempr` varchar(40) NOT NULL,
  `empresa_identtrib` varchar(15) NOT NULL,
  `empresa_fk_ciudad` int(11) NOT NULL,
  PRIMARY KEY (`empresa_id`),
  FOREIGN KEY (`empresa_fk_ciudad`) REFERENCES `ciudad` (`ciudad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla estadocivil
---
CREATE TABLE `estadocivil` (
  `estadocivil_id` int(11) NOT NULL AUTO_INCREMENT,
  `estadocivil_tipo` varchar(25) NOT NULL,
  PRIMARY KEY (`estadocivil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla genero
---
CREATE TABLE `genero` (
  `genero_id` int(11) NOT NULL AUTO_INCREMENT,
  `genero_sexo` varchar(15) NOT NULL,
  PRIMARY KEY (`genero_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla gestionempref
---
CREATE TABLE `gestionempref` (
  `gestionempref_id` int(11) NOT NULL AUTO_INCREMENT,
  `gestionempref_fk_empleado` int(11) NOT NULL,
  `gestionempref_fk_referencia` int(11) NOT NULL,
  `gestionempref_fk_tiporef` int(11) NOT NULL,
  PRIMARY KEY (`gestionempref_id`),
  FOREIGN KEY (`gestionempref_fk_empleado`) REFERENCES `empleado` (`empleado_id`),
  FOREIGN KEY (`gestionempref_fk_referencia`) REFERENCES `referencia` (`referencia_id`),
  FOREIGN KEY (`gestionempref_fk_tiporef`) REFERENCES `tiporef` (`tiporef_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla persona
---
CREATE TABLE `persona` (
  `persona_id` int(11) NOT NULL AUTO_INCREMENT,
  `persona_primernom` varchar(10) NOT NULL,
  `persona_segundonom` varchar(10) NOT NULL,
  `persona_primerapell` varchar(10) NOT NULL,
  `persona_segundoapell` varchar(10) NOT NULL,
  `persona_direccion` varchar(80) NOT NULL,
  PRIMARY KEY (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla referencia
---
CREATE TABLE `referencia` (
  `referencia_id` int(11) NOT NULL AUTO_INCREMENT,
  `referencia_fk_persona` int(11) NOT NULL,
  `referencia_fk_ciudad` int(11) NOT NULL,
  `referencia_fk_contacto` int(11) NOT NULL,
  `referencia_fk_tiporef` int(11) NOT NULL,
  PRIMARY KEY (`referencia_id`),
  FOREIGN KEY (`referencia_fk_persona`) REFERENCES `persona` (`persona_id`),
  FOREIGN KEY (`referencia_fk_ciudad`) REFERENCES `ciudad` (`ciudad_id`),
  FOREIGN KEY (`referencia_fk_contacto`) REFERENCES `contacto` (`contacto_id`),
  FOREIGN KEY (`referencia_fk_tiporef`) REFERENCES `tiporef` (`tiporef_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
---
--- Tabla tiporef
---
CREATE TABLE `tiporef` (
  `tiporef_id` int(11) NOT NULL AUTO_INCREMENT,
  `tiporef_tiporef` varchar(10) NOT NULL,
  PRIMARY KEY (`tiporef_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

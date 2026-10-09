-- =====================================================================
-- Examen Práctico Integrador — Programación III
-- Script de base de datos de la pareja
--
-- Etapa 1: versión preliminar (base de datos y tablas).
-- Etapa 2: versión definitiva (tablas, restricciones y datos de prueba).
--
-- Ejecutar:  mysql -u root -p < database/schema.sql
-- El nombre de la base de datos debe coincidir con db.url en db.properties.
-- =====================================================================

DROP DATABASE IF EXISTS examen_progra3;
CREATE DATABASE examen_progra3
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_spanish_ci;

USE examen_progra3;

-- ---------------------------------------------------------------------
-- Tablas
-- Cree primero las tablas sin llaves foráneas y luego las que dependen
-- de ellas. Use PRIMARY KEY, FOREIGN KEY, NOT NULL, UNIQUE y CHECK.
-- ---------------------------------------------------------------------

-- Ejemplo de formato (reemplácelo por las tablas de su caso):
--
-- CREATE TABLE equipo (
--     codigo      VARCHAR(10)  PRIMARY KEY,
--     descripcion VARCHAR(100) NOT NULL,
--     estado      VARCHAR(20)  NOT NULL DEFAULT 'DISPONIBLE'
-- );
--
-- CREATE TABLE prestamo (
--     id              INT AUTO_INCREMENT PRIMARY KEY,
--     equipo_codigo   VARCHAR(10) NOT NULL,
--     fecha_prestamo  DATE        NOT NULL,
--     CONSTRAINT fk_prestamo_equipo FOREIGN KEY (equipo_codigo)
--         REFERENCES equipo (codigo)
-- );


-- ---------------------------------------------------------------------
-- Datos de prueba (Etapa 2)
-- ---------------------------------------------------------------------

-- INSERT INTO equipo (codigo, descripcion) VALUES ('EQ-01', 'Proyector Epson');

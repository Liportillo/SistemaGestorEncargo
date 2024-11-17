-- Creación de la base de datos
CREATE DATABASE SistemaGestorEncargos;
USE SistemaGestorEncargos;

-- Creación de la tabla cliente
CREATE TABLE cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    razonSocial VARCHAR(255),
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    fechaRegistro DATE NOT NULL,
    tipoCliente ENUM('persona', 'empresa') NOT NULL,
    estado ENUM('activo', 'inactivo') NOT NULL
);

-- Creación de la tabla usuario con el rol 'profesional'
CREATE TABLE usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    rol ENUM('administrador', 'contador', 'abogado', 'profesional') NOT NULL,
    fechaCreacion DATE NOT NULL,
    estado ENUM('activo', 'inactivo') NOT NULL
);

-- Creación de la tabla encargo
CREATE TABLE encargo (
    idEncargo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    estado ENUM('pendiente', 'en revisión', 'finalizado') NOT NULL,
    prioridad ENUM('urgente', 'común') NOT NULL,
    idCliente INT,
    idUsuario INT,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente) ON DELETE CASCADE,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Creación de la tabla reporte
CREATE TABLE reporte (
    idReporte INT AUTO_INCREMENT PRIMARY KEY,
    fechaReporte DATE NOT NULL,
    formato ENUM('Reporte jurídico', 'Reporte contable', 'Reporte jurídico-contable') NOT NULL,
    rutaReporte VARCHAR(255) NOT NULL,
    idEncargo INT,
    FOREIGN KEY (idEncargo) REFERENCES encargo(idEncargo) ON DELETE CASCADE
);

-- Creación de la tabla tipoDocumento
CREATE TABLE tipoDocumento (
    idTipoDoc INT AUTO_INCREMENT PRIMARY KEY,
    tipoDoc VARCHAR(100) NOT NULL
);

-- Creación de la tabla documento
CREATE TABLE documento (
    idDocumento INT AUTO_INCREMENT PRIMARY KEY,
    nombreDocumento VARCHAR(255) NOT NULL,
    fechaSubida DATE NOT NULL,
    rutaArchivo VARCHAR(255) NOT NULL,
    estado ENUM('pendiente', 'en revisión', 'finalizado') NOT NULL,
    idEncargo INT,
    idTipoDoc INT,
    FOREIGN KEY (idEncargo) REFERENCES encargo(idEncargo) ON DELETE CASCADE,
    FOREIGN KEY (idTipoDoc) REFERENCES tipoDocumento(idTipoDoc) ON DELETE CASCADE
);

-- Creación de la tabla historialModificaciones
CREATE TABLE historialModificaciones (
    idHistorial INT AUTO_INCREMENT PRIMARY KEY,
    fechaModificacion DATE NOT NULL,
    descripcion TEXT NOT NULL,
    idDocumento INT,
    idUsuario INT,
    FOREIGN KEY (idDocumento) REFERENCES documento(idDocumento) ON DELETE CASCADE,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

-- Inserciones en la tabla tipoDocumento
INSERT INTO tipoDocumento (tipoDoc) VALUES
('Contrato'),
('Informe'),
('Balance contable');

-- Inserciones en la tabla usuario
INSERT INTO usuario (nombre, apellido, nombreUsuario, contraseña, correo, rol, fechaCreacion, estado) VALUES
('Lisandro', 'Portillo', 'LISPORTILLO', 'lisportillo@gmail.com', 'casa111', 'administrador', '2024-01-01', 'activo');
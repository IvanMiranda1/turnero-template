-- Creación de la tabla cliente
CREATE TABLE cliente(
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(50) NOT NULL,
    dni BIGINT NOT NULL UNIQUE
);

-- Creación de la tabla servicio
CREATE TABLE servicio(
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    duracion_estimada_minutos INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    detalle TEXT
);

-- Creación de la tabla de roles
CREATE TABLE rol (
    id SERIAL PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE
);

-- Creación de la tabla de estados posibles para turno
CREATE TABLE estado_turno (
    id SERIAL PRIMARY KEY,
    nombre_estado VARCHAR(50) NOT NULL UNIQUE
);

-- Creación de la tabla de usuarios con clave foránea a la tabla de roles
CREATE TABLE usuario(
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    dni BIGINT NOT NULL UNIQUE,
    fk_rol INT NOT NULL, -- Clave foránea a la tabla de roles
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ultima_sesion TIMESTAMP,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (fk_rol) REFERENCES rol(id)
);

-- Creación de la tabla de turnos con claves foráneas a cliente, usuario, servicio y estado_turno
CREATE TABLE turno(
    id BIGSERIAL PRIMARY KEY,
    fecha_alta TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_turno TIMESTAMP NOT NULL,
    fk_estado INT NOT NULL,
    fk_cliente BIGINT NOT NULL,
    fk_usuario BIGINT NOT NULL,
    fk_servicio BIGINT NOT NULL,
    detalle TEXT,
    CONSTRAINT fk_turno_cliente FOREIGN KEY(fk_cliente) REFERENCES cliente(id),
    CONSTRAINT fk_turno_usuario FOREIGN KEY(fk_usuario) REFERENCES usuario(id),
    CONSTRAINT fk_turno_servicio FOREIGN KEY(fk_servicio) REFERENCES servicio(id),
    CONSTRAINT fk_turno_estado FOREIGN KEY (fk_estado) REFERENCES estado_turno(id)
);
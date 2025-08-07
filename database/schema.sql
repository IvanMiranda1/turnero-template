CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    rol VARCHAR(50) NOT NULL, -- Por ejemplo: 'ADMIN', 'CLIENTE'
    fecha_creacion TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    fk_usuario BIGINT UNIQUE REFERENCES usuario(id),
    nombre VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE profesional (
    id BIGSERIAL PRIMARY KEY,
    fk_usuario BIGINT UNIQUE REFERENCES usuario(id),
    nombre VARCHAR(255) NOT NULL,
    especialidad VARCHAR(255) NOT NULL
);

CREATE TABLE servicio (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    duracion_estimada_minutos INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    detalle TEXT
);

CREATE TABLE turno (
    id BIGSERIAL PRIMARY KEY,
    fecha_alta DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_turno TIMESTAMP NOT NULL,
    estado VARCHAR(50) NOT NULL, -- Por ejemplo: 'PENDIENTE', 'CONFIRMADO', 'CANCELADO'
    fk_cliente BIGINT REFERENCES cliente(id),
    fk_profesional BIGINT REFERENCES profesional(id),
    fk_servicio BIGINT REFERENCES servicio(id),
    detalle TEXT
);
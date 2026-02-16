# Se crea el container a partir de la imagen oficial de PostgreSQL, versión 16.1 con Alpine Linux
FROM postgres:16.1-alpine

# Se copia el script SQL a un directorio especial de PostgreSQL.
# Cualquier archivo .sql en este directorio será ejecutado la primera vez que el contenedor se inicie.
COPY ./schema.sql /docker-entrypoint-initdb.d/

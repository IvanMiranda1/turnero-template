# Base de la imagen de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR compilado de la aplicación Spring Boot al contenedor
# El nombre del archivo debe coincidir con el generado por el build de Maven
COPY target/turnero-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto por defecto para la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación Java al inicio del contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]

# Usar una imagen base de JDK 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR del proyecto al contenedor
COPY target/*.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8090

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
# Nombre del Proyecto
Test Angel Guerra

## Descripción
Este proyecto es una API REST para gestionar estudiantes.

## Tecnologías Utilizadas
- Spring Boot
- Spring Data JPA
- Hibernate
- Swagger
- MySQL

## Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/aguerralozano/test_f_oh.git

## Docker
```bash
mvnw.cmd clean package
docker build -t imagen-estudiante-jdk11 .
docker run -p 8091:8090 --name container-estudiante imagen-estudiante-jdk11
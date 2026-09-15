# Etapa 1: Construcción (Build)
# Utilizamos una imagen de Maven con Java 21 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo pom.xml y descargamos las dependencias
# (Hacer esto primero mejora la velocidad de compilación usando la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente de la aplicación
COPY src ./src

# Compilamos el proyecto y generamos el archivo .jar, saltando las pruebas unitarias para mayor velocidad
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
# Utilizamos una imagen mucho más ligera de Java 21 para ejecutar la aplicación
FROM eclipse-temurin:21-jre-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo .jar generado en la etapa anterior (Etapa 1)
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080 (el que usará Render por defecto)
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
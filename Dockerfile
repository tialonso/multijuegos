# Usar la imagen oficial de Maven con OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Configurar directorio de trabajo
WORKDIR /app

# Copiar archivos del proyecto al contenedor
COPY . .

# Construir el proyecto usando Maven
RUN mvn clean package -DskipTests

# Segunda etapa: Imagen final optimizada con solo el JAR
FROM openjdk:17-jdk-slim

# Configurar directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto en el que corre la app
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
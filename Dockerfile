# ===== ETAPA 1: Compilar el proyecto =====
# Cambiamos a una imagen que soporte Java 25
FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ===== ETAPA 2: Ejecutar el proyecto =====
# Usamos el JRE de Java 25 para correrlo
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
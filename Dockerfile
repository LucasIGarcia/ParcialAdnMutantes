# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine

# Configura el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado a la imagen
COPY build/libs/parcialAdnMutantes-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]

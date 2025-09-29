# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src/main/resources/fonts/jasperfont-calibri.jar ./
COPY src/main/resources/fonts/jasperfont-georgia.jar ./
RUN ./mvnw install:install-file -Dfile=./jasperfont-calibri.jar -DgroupId=com.andos.jasperfonts -DartifactId=jasperfont-calibri -Dversion=1.0 -Dpackaging=jar
RUN ./mvnw install:install-file -Dfile=./jasperfont-georgia.jar -DgroupId=com.andos.jasperfonts -DartifactId=jasperfont-georgia -Dversion=1.0 -Dpackaging=jar
RUN ./mvnw dependency:go-offline
COPY src/ ./src
RUN ./mvnw package -DskipTests

# Stage 2: Create a final lightweight image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
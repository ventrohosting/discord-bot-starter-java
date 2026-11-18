FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /src
COPY pom.xml .
COPY src ./src
RUN mvn -B -q package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /src/target/bot.jar bot.jar
RUN useradd -m bot
USER bot
CMD ["java", "-jar", "bot.jar"]

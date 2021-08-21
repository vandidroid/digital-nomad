# syntax=docker/dockerfile:1

FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine@sha256:d92dbf101ee9a55a059d0a556d7f45f811f2a3ba11ca29b0702ba2b563dff343
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system vandidroid && adduser -S -s /bin/false -G vandidroid vandidroid
COPY --from=build /project/target/digital-nomad-destinations-0.0.1-SNAPSHOT.jar /app/digital-nomad-destinations.jar
WORKDIR /app
RUN chown -R vandidroid:vandidroid /app
USER vandidroid
CMD "dumb-init" "java" "-jar" "digital-nomad-destinations.jar"


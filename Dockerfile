#ARG VERSION=8u151
#
#FROM openjdk:${VERSION}-jdk as BUILD
#
#COPY . /src
#WORKDIR /src
#RUN gradle wrapper
#RUN ./gradlew --no-daemon shadowJar
#
#FROM openjdk:${VERSION}-jre
#
#COPY --from=BUILD /src/build/libs/goppeav-kotlin-2022-12-1.0.jar /bin/runner/run.jar
#WORKDIR /bin/runner
#
#CMD ["java","-jar","run.jar"]




FROM gradle:8.0.0-jdk17-alpine AS TEMP_BUILD_IMAGE
#ENV ARTIFACT_NAME=goppeav-kotlin-2022-12-1.0.jar
WORKDIR $APP_HOME
COPY . $APP_HOME
RUN gradle wrapper
#
#COPY gradle $APP_HOME/gradle
#COPY --chown=gradle:gradle . /home/gradle/src
#USER root
RUN #chown -R gradle /home/gradle/src
RUN #chown -R gradlew /home/gradle/src

RUN #gradle run

CMD ls
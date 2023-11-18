FROM eclipse-temurin:20-jdk

ARG GRADLE_VERSION=8.2

RUN apt-get update && apt-get install -yq make unzip

RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip \
    && rm gradle-${GRADLE_VERSION}-bin.zip

ENV GRADLE_HOME=/opt/gradle

RUN mv gradle-${GRADLE_VERSION} ${GRADLE_HOME}

ENV PATH=$PATH:$GRADLE_HOME/bin

WORKDIR /project

COPY /app/src/main app/src/main
COPY /app/build.gradle app/build.gradle
COPY /app/settings.gradle app/settings.gradle
COPY /gradle gradle
COPY /build.gradle .
COPY /settings.gradle .
COPY /gradlew .
COPY /Dockerfile .

RUN ./gradlew -p app/ --no-daemon dependencies

RUN ./gradlew -p app/ --no-daemon build

ARG JAR_FILE=app/build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

CMD java -jar app.jar

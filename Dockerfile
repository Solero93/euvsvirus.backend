FROM gradle:6.3-jdk8 AS buildContainer
ARG APP_HOME=/usr/app/
WORKDIR ${APP_HOME}
COPY build.gradle.kts settings.gradle.kts ${APP_HOME}
COPY src/ ${APP_HOME}/src
RUN gradle clean build
WORKDIR ${APP_HOME}/build/libs
RUN jar -xf *.jar


FROM openjdk:8-jdk-alpine AS runContainer
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=/usr/app/build/libs
COPY --from=buildContainer ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=buildContainer ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=buildContainer ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.euvsvirus.euvsvirus.EuvsvirusApplicationKt"]
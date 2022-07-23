FROM maven:3.8.6-openjdk-11-slim
MAINTAINER Andrey
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve
ADD ./package.json $PROJECT_DIR
ADD ./webpack.config.js $PROJECT_DIR
ADD ./src/ $PROJECT_DIR/src
RUN mvn install -DskipTests
FROM openjdk:11-jre-slim
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
COPY --from=0 $PROJECT_DIR/target/work-react* $PROJECT_DIR/
EXPOSE 8081
CMD ["java", "-jar", "/opt/project/work-react-1.0.jar"]
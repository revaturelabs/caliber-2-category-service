FROM  maven:3.6.1-jdk-8
EXPOSE 80
ENV KUBERNETES_NAMESPACE=default
ENV message=welcome
ARG JAR_FILE
ENV JAR_FILE=$JAR_FILE

ARG SPRING_PROFILES
ENV spring_profiles_active=$SPRING_PROFILES

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/urandom -jar /usr/src/app/target/$JAR_FILE" ]




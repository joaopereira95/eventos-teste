FROM maven:3-openjdk-17 as maven
LABEL maintainer 'Joao Eduardo Pereira'

COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests=true

FROM openjdk:17
LABEL maintainer 'Joao Eduardo Pereira'
WORKDIR /target
COPY --from=maven target/*.jar ./

COPY scripts/wait-for-it.sh ./

RUN chmod +x wait-for-it.sh

ENTRYPOINT ["bash", "./wait-for-it.sh", "event-manager-db:3306", "-t", "60", "--", "bash", "./wait-for-it.sh", "rabbitmq:5672", "-t", "60", "--"]

CMD java -jar scheduler-0.0.1-SNAPSHOT.jar

FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM quay.io/wildfly/wildfly:27.0.1.Final
ENV DEPLOY_DIR /opt/jboss/wildfly/standalone/deployments/


COPY --from=builder /build/target/jakartaee-hello-world.war ${DEPLOY_DIR}/ROOT.war

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

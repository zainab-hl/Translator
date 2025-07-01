
FROM quay.io/wildfly/wildfly:latest
ENV DEPLOY_DIR /opt/jboss/wildfly/standalone/deployments/

# Copy the WAR file into the deployments folder
COPY target/jakartaee-hello-world.war ${DEPLOY_DIR}

# WildFly's default HTTP port
EXPOSE 8080

# Run WildFly in standalone mode
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

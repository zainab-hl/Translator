# Use official WildFly image with JDK 17
FROM quay.io/wildfly/wildfly:latest

# Set environment variable for deployment
ENV DEPLOY_DIR /opt/jboss/wildfly/standalone/deployments/

# Copy your WAR file into the deployments folder
COPY target/translator.war ${DEPLOY_DIR}

# Expose WildFly's default HTTP port
EXPOSE 8080

# Run WildFly in standalone mode
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

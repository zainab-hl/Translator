# Use the official WildFly image
FROM jboss/wildfly:latest

# Set the location inside the container where the app will go
COPY target/*.war /opt/jboss/wildfly/standalone/deployments/

# Expose port (WildFly default)
EXPOSE 8080

FROM bitnami/wildfly:latest

COPY target/lab2.war /opt/bitnami/wildfly/standalone/deployments/ROOT.war

CMD ["/opt/bitnami/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
FROM bitnami/wildfly:latest

COPY target/lab3.war /opt/bitnami/wildfly/standalone/deployments/ROOT.war
COPY postgres-ds.xml /opt/bitnami/wildfly/standalone/deployments/
COPY driver/postgresql-42.7.4.jar /opt/bitnami/wildfly/standalone/deployments/

CMD ["/opt/bitnami/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
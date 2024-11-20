FROM bitnami/wildfly:latest

COPY target/lab3.war /opt/bitnami/wildfly/standalone/deployments/ROOT.war

COPY mysql-files/wait-for-it.sh /opt/wait-for-it.sh

COPY postgres-ds.xml /opt/bitnami/wildfly/standalone/deployments/
COPY driver/postgresql-42.7.4.jar /opt/bitnami/wildfly/standalone/deployments/

COPY mysql-ds.xml /opt/bitnami/wildfly/standalone/deployments/
COPY driver/mysql-connector-j-9.1.0.jar /opt/bitnami/wildfly/standalone/deployments/

USER root
RUN chmod +x /opt/wait-for-it.sh
USER 1001

CMD /opt/wait-for-it.sh mysqlDB:3306 --timeout=60 --strict -- /opt/bitnami/wildfly/bin/standalone.sh -b 0.0.0.0

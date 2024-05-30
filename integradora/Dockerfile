FROM tomcat:latest

COPY wait-for-it.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/wait-for-it.sh

COPY build/libs/integradora.war /usr/local/tomcat/webapps/

CMD ["wait-for-it.sh", "mysql:3306", "--", "wait-for-it.sh", "mongo:27017", "--", "catalina.sh", "run"]

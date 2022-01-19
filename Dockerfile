FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/findbook.war /usr/local/tomcat/webapps/findbook.war
CMD ["catalina.sh","run"]
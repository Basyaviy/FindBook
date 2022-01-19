FROM tomcat:9.0
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/findbook.war /usr/local/tomcat/webapps/findbook.war
CMD ["catalina.sh","run"]
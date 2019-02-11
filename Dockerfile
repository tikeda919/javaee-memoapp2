FROM tomcat:8.5

COPY target/memoapp2.war /usr/local/tomcat/webapps/

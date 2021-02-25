FROM maven:3.6 AS memoapp-build

WORKDIR /usr/local/src
RUN git clone https://github.com/tikeda919/javaee-memoapp2.git \
    && cd javaee-memoapp2 \
    && mvn package


FROM tomcat:8.5

COPY --from=memoapp-build /usr/local/src/javaee-memoapp2/target/memoapp2.war /usr/local/tomcat/webapps/

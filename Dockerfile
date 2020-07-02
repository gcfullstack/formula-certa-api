FROM adoptopenjdk/openjdk11:latest
#FROM tomcat:9-jre8
MAINTAINER SFA
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
RUN mkdir -p /mnt/sfa-fs-app
RUN mkdir -p /opt/app
#ADD target/api-novo.jar /usr/local/tomcat/webapps/
ENV TZ=America/Sao_Paulo
ADD target/api-novo.jar /opt/app
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "/opt/app/api-novo.jar"]

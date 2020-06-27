FROM openjdk:11.0.5-jdk
COPY target/second-0.0.1.jar /var/www/second-0.0.1.jar
WORKDIR /var/www

EXPOSE 8081
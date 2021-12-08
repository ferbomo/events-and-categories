FROM openjdk:11
MAINTAINER ferbomo@gmail.com
COPY target/categories-and-events-1.0-SNAPSHOT.jar categories-and-events-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/categories-and-events-1.0-SNAPSHOT.jar"]
CMD [""]
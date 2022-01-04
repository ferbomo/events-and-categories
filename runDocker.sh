#!/bin/bash
mvn clean install
docker build --build-arg JAR_FILE=build/libs/\*.jar -t stubhub/categories .
docker run -i -t stubhub/categories
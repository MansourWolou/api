# je pars de cette image parceque il parait que openjdk n'est plus maintenu.
FROM eclipse-temurin:17
COPY target/demo-0.0.1-SNAPSHOT.jar /opt/app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/opt/app/demo-0.0.1-SNAPSHOT.jar"]
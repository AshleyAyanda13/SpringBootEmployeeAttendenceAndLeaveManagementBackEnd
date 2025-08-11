 # Use a lightweight Java 21 runtime image
 FROM eclipse-temurin:21-jre

 # Set working directory inside the container
 WORKDIR /app

 # Copy the built jar file into the container
 COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

 # Expose the port your app runs on
 EXPOSE 8080

 # Run the application
 ENTRYPOINT ["java", "-jar", "app.jar"]

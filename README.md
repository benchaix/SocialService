# SocialService
A basic http server serving RESTful API

##Info
Coded in approximately 2 days, this HTTP server serves a RESTful API with no use of third party framework as Jersey or Spring. It was a nice exercice :)
I used Java 8 and Maven 3.3 to manage dependencies (JUnit, HttpClient and Gson) and build the application

##Compilation and execution
To compile this Java application into a single jar use the Maven command below:
>mvn clean compile assembly:single

it will produce a jar "social-service-benchaix-0.0.1-SNAPSHOT.jar" in target directory that you can use to launch the HTTP server.

>java -jar social-service-benchaix-0.0.1-SNAPSHOT.jar

Next you can access the API on http://localhost:8000

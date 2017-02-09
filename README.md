# SocialService
A basic http server serving RESTful API

##Info
Coded in approximately 2 days, this http server expose a RESTful api without the help of any framework like Jersey or Spring. It was a nice exercice :)

##Compilation and execution
To compile this Java application into a single jar use the Maven command below:
>mvn clean compile assembly:single

it will produce a jar "social-service-benchaix-0.0.1-SNAPSHOT.jar" in target directory that you can use to launch the HTTP server.

>java -jar social-service-benchaix-0.0.1-SNAPSHOT.jar

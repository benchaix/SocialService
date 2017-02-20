# SocialService
A basic http server serving RESTful API microservice

##About
This HTTP server serves a RESTful API with no use of third party framework like Jersey or Spring but with the use of com.sun.net.httpserver.HtppServer.
We have a RESTful resource called "story", which we need to track how many people like and dislike the object, referred to as the "popularity".
We need endpoints for the following actions:

GET /story/{id} - Retrieve the current popularity of a story/{id}
POST /story/{id} - Explicitly set the current popularity of a story
PUT /story/{id}/like - "Like" the story (increment the story's popularity by one)
PUT /story/{id}/dislike - "Dislike" the story (decrement the story's popularity by one)

On success each endpoint should return a HTTP status code of 200, with a response body containing the popularity of the story being acted on after the action completes.
On error an appropriate error should be returned such as 404 for missing objects. An example of a successful response body would be:

{
	"popularity": 40
}


## Technologies
- Java 8
- Maven 3.3 to build the application and manage dependencies (JUnit, HttpClient and Gson)


##Compilation and execution
To compile this Java application into a single jar use the Maven command below:
>mvn clean compile assembly:single

it will produce a jar "social-service-benchaix-0.0.1-SNAPSHOT.jar" in target directory that you can use to launch the HTTP server.

>java -jar social-service-benchaix-0.0.1-SNAPSHOT.jar

Next you can access the API on http://localhost:8000
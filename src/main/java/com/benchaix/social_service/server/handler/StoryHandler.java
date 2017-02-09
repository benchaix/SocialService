package com.benchaix.social_service.server.handler;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import com.benchaix.social_service.model.Story;
import com.benchaix.social_service.server.CustomHTTPServer;
import com.benchaix.social_service.service.StoryService;
import com.benchaix.social_service.service.impl.CustomStoryService;
import com.sun.net.httpserver.HttpExchange;

/**
 * Handler for story queries: /story/{id} /story/{id}/like /story/{id}/dislike
 * 
 * @author Ben Chaix
 */
public class StoryHandler extends DefaultHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    super.handle(exchange);

    // CustomStoryService is a singleton and can be reused in other handlers
    StoryService storyService = CustomStoryService.getInstance();
    URI uri = exchange.getRequestURI();
    String path = uri.getPath();
    String[] pathElements = path.split("/");

    // parse story id
    Integer storyId = null;
    if (pathElements.length > 2) {
      try {
        storyId = Integer.parseInt(pathElements[2]);
      } catch (NumberFormatException ex) {
        // cannot get a valid id
      }
    }
    if (storyId == null) {
      CustomHTTPServer.writeResponseNotFound(exchange, "");
      return;
    }

    // parse action (like|dislike)
    String action = "";
    if (pathElements.length > 3) {
      action = pathElements[3];
    }

    String requestMethod = exchange.getRequestMethod();

    // Retrieve the current popularity of a story
    if (METHOD_GET.equalsIgnoreCase(requestMethod) && "".equals(action)) {
      Optional<String> optionalJSONString = storyService.getStoryJSON(storyId);
      CustomHTTPServer.writeJSONResponse(exchange, optionalJSONString);
      return;
    }

    // Explicitly set the current popularity of a story
    if (METHOD_POST.equalsIgnoreCase(requestMethod)) {
      String jsonString = getPostBody(exchange);

      Story deserializedStory = Story.fromJSONString(jsonString);
      if (deserializedStory == null) {
        String response = "Not a valid JSON";
        CustomHTTPServer.writeResponseBadRequest(exchange, response.toString());
        return;
      }

      Optional<String> optionalJSONString = storyService.setStoryPopularityAndGetJSON(storyId,
          deserializedStory.getPopularity());
      CustomHTTPServer.writeJSONResponse(exchange, optionalJSONString);
      return;
    }

    // Like or dislike the story
    if (METHOD_PUT.equalsIgnoreCase(requestMethod)) {

      if ("like".equals(action)) {
        Optional<String> optionalJSONString = storyService.incrementStoryPopularityAndGetJSON(storyId);
        CustomHTTPServer.writeJSONResponse(exchange, optionalJSONString);
        return;

      } else if ("dislike".equals(action)) {
        Optional<String> optionalJSONString = storyService.decrementStoryPopularityAndGetJSON(storyId);
        CustomHTTPServer.writeJSONResponse(exchange, optionalJSONString);
        return;
      }
    }

    CustomHTTPServer.writeResponseBadRequest(exchange, "");
  }
}

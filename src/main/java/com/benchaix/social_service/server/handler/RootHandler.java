package com.benchaix.social_service.server.handler;

import java.io.IOException;

import com.benchaix.social_service.server.CustomHTTPServer;
import com.sun.net.httpserver.HttpExchange;

/**
 * Root handler
 * 
 * @author Ben Chaix
 */
public class RootHandler extends DefaultHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    super.handle(exchange);

    String response = "Hint: use story/{id} URL syntax";
    CustomHTTPServer.writeResponseOK(exchange, response.toString());
  }
}

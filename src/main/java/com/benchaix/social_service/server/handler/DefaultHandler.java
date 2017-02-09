package com.benchaix.social_service.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Default handler to extend
 * 
 * @author Ben Chaix
 */
public class DefaultHandler implements HttpHandler {

  public static final String METHOD_GET = "GET";
  public static final String METHOD_POST = "POST";
  public static final String METHOD_PUT = "PUT";

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    // add here filter on content type, charset, method, ...

    // determine content type example:
    // Headers reqHeaders = exchange.getRequestHeaders();
    // String contentType = reqHeaders.getFirst("Content-Type");
  }

  protected String getPostBody(HttpExchange exchange) throws IOException {
    if (METHOD_POST.equalsIgnoreCase(exchange.getRequestMethod())) {
      InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
      BufferedReader br = new BufferedReader(isr);
      return br.readLine();
    }
    return "";
  }
}

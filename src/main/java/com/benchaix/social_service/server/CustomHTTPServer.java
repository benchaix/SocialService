package com.benchaix.social_service.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.Executors;

import com.benchaix.social_service.server.handler.RootHandler;
import com.benchaix.social_service.server.handler.StoryHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * Custom HTTP server using com.sun.net.httpserver.HttpServer
 * (http://localhost:8000)
 * 
 * @author Ben Chaix
 */
public class CustomHTTPServer {
  private static final String PROTOCOL = "http";
  private static final String HOSTNAME = "localhost";
  private static final int PORT = 8000;
  // Backlog is the maximum number of queued incoming connections to allow on
  // the listening socket. Queued TCP connections exceeding this limit may be
  // rejected by the TCP implementation.
  // If this value is less than or equal to zero, then a system default value is
  // used.
  private static final int BACKLOG = 0;
  private static final Charset CHARSET = StandardCharsets.UTF_8;

  private static final int STATUS_OK = 200;
  private static final int STATUS_BAD_REQUEST = 400;
  private static final int STATUS_NOT_FOUND = 404;

  public CustomHTTPServer() {
    try {
      createServer();
      System.out.println("Server listening on port " + PORT + " - " + PROTOCOL + "://" + HOSTNAME + ":" + PORT);
    } catch (Exception ex) {
      System.out.println("Cannot bind address or already in use: " + PROTOCOL + "://" + HOSTNAME + ":" + PORT);
      ex.printStackTrace();
    }
  }

  private void createServer() throws Exception {
    final HttpServer server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), BACKLOG);
    server.createContext("/", new RootHandler());
    server.createContext("/story", new StoryHandler());
    // Creates a thread pool that creates new threads as needed, but will reuse
    // previously constructed threads when they are available.
    server.setExecutor(Executors.newCachedThreadPool());
    server.start();
  }

  public static void writeJSONResponse(HttpExchange exchange, Optional<String> optionalJSONString) throws IOException {
    String response = "";
    if (optionalJSONString.isPresent()) {
      exchange.getResponseHeaders().set("Content-Type", "application/json; charset=" + CHARSET.name());
      response = optionalJSONString.get();
      writeResponseOK(exchange, response.toString());
    } else {
      writeResponseNotFound(exchange, response.toString());
    }
  }

  public static void writeResponseOK(HttpExchange exchange, String response) throws IOException {
    writeResponse(exchange, response, STATUS_OK);
  }

  public static void writeResponseNotFound(HttpExchange exchange, String response) throws IOException {
    writeResponse(exchange, response, STATUS_NOT_FOUND);
  }

  public static void writeResponseBadRequest(HttpExchange exchange, String response) throws IOException {
    writeResponse(exchange, response, STATUS_BAD_REQUEST);
  }

  public static void writeResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
    exchange.sendResponseHeaders(statusCode, response.getBytes().length);
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}

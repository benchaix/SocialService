package com.benchaix.social_service.server.handler;

import java.util.concurrent.Callable;

/**
 * Define an abstract URL Object
 * 
 * @author Ben Chaix
 */
public abstract class CallURL implements Callable<String> {

  protected final String URL;
  protected final String body;

  public CallURL(String URL, String body) {
    this.URL = URL;
    this.body = body;
  }

  @Override
  public String call() throws Exception {
    return null;
  }
}

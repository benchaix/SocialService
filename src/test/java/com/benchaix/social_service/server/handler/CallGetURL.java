package com.benchaix.social_service.server.handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Define a callable URL Object
 * 
 * @author Ben Chaix
 */
public class CallGetURL extends CallURL {

  public CallGetURL(String URL, String body) {
    super(URL, body);
  }

  @Override
  public String call() throws Exception {
    HttpGet httpGet = new HttpGet(URL);
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpGet);
    HttpEntity entity = httpResponse.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    return responseString;
  }
}

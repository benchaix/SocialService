package com.benchaix.social_service.server.handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Define a callable URL Object
 * 
 * @author Ben Chaix
 */
public class CallPutURL extends CallURL {

  public CallPutURL(String URL, String body) {
    super(URL, body);
  }

  @Override
  public String call() throws Exception {
    HttpPut httpPut = new HttpPut(URL);
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPut);
    HttpEntity entity = httpResponse.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    return responseString;
  }
}

package com.benchaix.social_service.server.handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Define a callable URL Object
 * 
 * @author Ben Chaix
 */
public class CallPostURL extends CallURL {

  public CallPostURL(String URL, String body) {
    super(URL, body);
  }

  @Override
  public String call() throws Exception {
    HttpPost httpPost = new HttpPost(URL);
    HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
    httpPost.setEntity(entity);

    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
    entity = httpResponse.getEntity();
    String responseString = EntityUtils.toString(entity, "UTF-8");
    return responseString;
  }
}

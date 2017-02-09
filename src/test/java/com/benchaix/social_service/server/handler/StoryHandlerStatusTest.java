package com.benchaix.social_service.server.handler;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test returned status code for valid / invalid story id / URL
 * 
 * @author Ben Chaix
 */
public class StoryHandlerStatusTest extends StoryHandlerConfig {

  @Test
  public void whenRetrievingStory20Popularity_ThenStatus200IsReceived() throws ClientProtocolException, IOException {
    HttpUriRequest request = new HttpGet(BASE_URL + "/story/20");
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
    Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
  }

  @Test
  public void whenRetrievingNonExistingStoryPopularity_ThenStatus404IsReceived()
      throws ClientProtocolException, IOException {
    // only 100 stories stored in memory
    HttpUriRequest request = new HttpGet(BASE_URL + "/story/200");
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
    Assert.assertEquals(404, httpResponse.getStatusLine().getStatusCode());
  }

  @Test
  public void whenCallingBadURL_ThenStatus404IsReceived() throws ClientProtocolException, IOException {
    HttpUriRequest request = new HttpGet(BASE_URL + "/story/test");
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
    Assert.assertEquals(404, httpResponse.getStatusLine().getStatusCode());
  }
}

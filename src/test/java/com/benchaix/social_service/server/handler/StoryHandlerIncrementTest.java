package com.benchaix.social_service.server.handler;

import org.junit.Assert;
import org.junit.Test;

import com.benchaix.social_service.utils.JSONUtils;

/**
 * Increment popularity test
 * 
 * @author Ben Chaix
 */
public class StoryHandlerIncrementTest extends StoryHandlerConfig {

  @Test
  public void whenIncrementingStory20Popularity_ThenPopularityIs31() throws Exception {

    // Set the story 50 to a popularity of 30
    CallPostURL postPopularity = new CallPostURL(BASE_URL + "/story/50", "{\"popularity\":30}");
    postPopularity.call();

    Thread.sleep(2000);

    CallPutURL putPopularity = new CallPutURL(BASE_URL + "/story/50/like", "");
    String prettyJson = JSONUtils.getPrettyJSONString("{\"popularity\":31}");

    Assert.assertEquals(prettyJson, putPopularity.call());
  }
}

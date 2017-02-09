package com.benchaix.social_service.server.handler;

import org.junit.Assert;
import org.junit.Test;

import com.benchaix.social_service.utils.JSONUtils;

/**
 * Decrement popularity test
 * 
 * @author Ben Chaix
 */
public class StoryHandlerDecrementTest extends StoryHandlerConfig {

  @Test
  public void whenDecrementingStory20Popularity_ThenPopularityIs29() throws Exception {

    // Set the story 50 to a popularity of 30
    CallPostURL postPopularity = new CallPostURL(BASE_URL + "/story/50", "{\"popularity\":30}");
    postPopularity.call();

    Thread.sleep(2000);

    CallPutURL putPopularity = new CallPutURL(BASE_URL + "/story/50/dislike", "");
    String prettyJson = JSONUtils.getPrettyJSONString("{\"popularity\":29}");

    Assert.assertEquals(prettyJson, putPopularity.call());
  }
}

package com.benchaix.social_service.server.handler;

import org.junit.Assert;
import org.junit.Test;

import com.benchaix.social_service.utils.JSONUtils;

/**
 * Explicitly set the popularity of a story
 * 
 * @author Ben Chaix
 */
public class StoryHandlerPostTest extends StoryHandlerConfig {

  @Test
  public void whenIncrementingStory20Popularity_ThenPopularityIs31() throws Exception {

    // Set the story 20 to a popularity of 30
    CallPostURL postPopularity = new CallPostURL(BASE_URL + "/story/20", "{\"popularity\":30}");
    postPopularity.call();

    String prettyJson = JSONUtils.getPrettyJSONString("{\"popularity\":30}");

    Assert.assertEquals(prettyJson, postPopularity.call());
  }
}

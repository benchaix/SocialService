package com.benchaix.social_service.model.serializer;

import org.junit.Assert;
import org.junit.Test;

import com.benchaix.social_service.model.Story;

/**
 * Deserialization test
 * 
 * @author Ben Chaix
 */
public class StoryDeserializerTest {

  @Test
  public void whenDeserializeStoryFromJSON_ThenExpectedPopularityIs() {
    Story story = Story.fromJSONString("{\"popularity\":10}");
    Assert.assertEquals(10L, (long) story.getPopularity());
  }
}

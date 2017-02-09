package com.benchaix.social_service.model.serializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.benchaix.social_service.model.Story;
import com.benchaix.social_service.utils.JSONUtils;

/**
 * Serialization test
 * 
 * @author Ben Chaix
 */
public class StorySerializerTest {

  private Story story;

  @Before
  public void setUp() throws Exception {
    // popularity is set to 10 as default
    story = new Story(1, 10L);
  }

  @Test
  public void whenSerializeStoryToJSON_ThenExpectedJSONIs() {
    String prettyJson = JSONUtils.getPrettyJSONString("{\"popularity\":10}");
    Assert.assertEquals(prettyJson, story.toJSONString());
  }
}

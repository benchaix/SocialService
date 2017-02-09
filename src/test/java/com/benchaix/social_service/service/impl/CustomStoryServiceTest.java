package com.benchaix.social_service.service.impl;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.benchaix.social_service.service.StoryService;
import com.benchaix.social_service.utils.JSONUtils;

/**
 * Returned JSON actions test
 * 
 * @author Ben Chaix
 */
public class CustomStoryServiceTest {

  StoryService storyService = CustomStoryService.getInstance();

  @Test
  public void whenRetrievingStory1JSON_ThenExpectedJSONIs() {
    Optional<String> optionalJSONString = storyService.getStoryJSON(1);

    String jsonString = "{\"popularity\":0}";
    String prettyJson = JSONUtils.getPrettyJSONString(jsonString);

    Assert.assertEquals(prettyJson, optionalJSONString.get());
  }

  @Test
  public void whenRetrievingNonExistingStoryJSON_ThenExpectedJSONIs() {
    Optional<String> optionalJSONString = storyService.getStoryJSON(200);
    Assert.assertEquals(Optional.empty(), optionalJSONString);
  }

  @Test
  public void whenSettingStory2PopularityTo100_ThenExpectedJSONIs() {
    Optional<String> optionalJSONString = storyService.setStoryPopularityAndGetJSON(2, 100L);

    String jsonString = "{\"popularity\":100}";
    String prettyJson = JSONUtils.getPrettyJSONString(jsonString);

    Assert.assertEquals(prettyJson, optionalJSONString.get());
  }

  @Test
  public void whenIncrementStory3Popularity_ThenExpectedJSONIs() {
    Optional<String> optionalJSONString = storyService.incrementStoryPopularityAndGetJSON(3);

    String jsonString = "{\"popularity\":1}";
    String prettyJson = JSONUtils.getPrettyJSONString(jsonString);

    Assert.assertEquals(prettyJson, optionalJSONString.get());
  }

  @Test
  public void whenDecrementStory4Popularity_ThenExpectedJSONIs() {
    Optional<String> optionalJSONString = storyService.decrementStoryPopularityAndGetJSON(4);

    String jsonString = "{\"popularity\":-1}";
    String prettyJson = JSONUtils.getPrettyJSONString(jsonString);

    Assert.assertEquals(prettyJson, optionalJSONString.get());
  }
}

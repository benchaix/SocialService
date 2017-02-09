package com.benchaix.social_service.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Setting popularity story tests
 * 
 * @author Ben Chaix
 */
public class StoryTest {

  private Story story;

  @Before
  public void setUp() throws Exception {
    // popularity is set to 10 as default
    story = new Story(1, 10L);
  }

  @Test
  public void whenSettingPopularityTo25_ThenPopularityIs25() {
    story.setPopularity(25L);
    Assert.assertEquals(25L, (long) story.getPopularity());
  }

  @Test
  public void whenIncrementingPopularityBy1_ThenPopularityIs11() {
    story.incrementPopularity();
    Assert.assertEquals(11L, (long) story.getPopularity());
  }

  @Test
  public void whenIncrementingPopularityBy5_ThenPopularityIs15() {
    for (int i = 0; i < 5; i++) {
      story.incrementPopularity();
    }
    Assert.assertEquals(15L, (long) story.getPopularity());
  }

  @Test
  public void whenDecrementingPopularityBy1_ThenPopularityIs9() {
    story.decrementPopularity();
    Assert.assertEquals(9L, (long) story.getPopularity());
  }

  @Test
  public void whenDecrementingPopularityBy4_ThenPopularityIs6() {
    for (int i = 0; i < 4; i++) {
      story.decrementPopularity();
    }
    Assert.assertEquals(6L, (long) story.getPopularity());
  }

  @Test
  public void whenSettingTo5DecrementingBy10IncrementingBy2_ThenPopularityIsMinus3() {
    story.setPopularity(5L);
    for (int i = 0; i < 10; i++) {
      story.decrementPopularity();
    }
    for (int i = 0; i < 2; i++) {
      story.incrementPopularity();
    }
    Assert.assertEquals(-3L, (long) story.getPopularity());
  }
}
package com.benchaix.social_service.persistence.dao.impl;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.benchaix.social_service.model.Story;
import com.benchaix.social_service.persistence.dao.StoryDAO;

/**
 * Data access object tests
 * 
 * @author Ben Chaix
 */
public class InMemoryStoryDAOTest {

  StoryDAO storyDAO = new InMemoryStoryDAO();

  @Test
  public void whenGettingStory1_ThenPopularityIs0() {
    Optional<Story> optionalStory = storyDAO.getStoryById(1);
    Assert.assertEquals(0L, (long) optionalStory.get().getPopularity());
  }

  @Test
  public void whenSettingStory1PopularityTo10_ThenPopularityIs10() {
    Optional<Story> optionalStory = storyDAO.setStoryPopularity(1, 10L);
    Assert.assertEquals(10L, (long) optionalStory.get().getPopularity());
  }

  @Test
  public void whenIncrementingStory2PopularityBy1_ThenPopularityIs1() {
    Optional<Story> optionalStory = storyDAO.incrementStoryPopularity(2);
    Assert.assertEquals(1L, (long) optionalStory.get().getPopularity());
  }

  @Test
  public void whenDecrementingStory3PopularityBy1_ThenPopularityIs1() {
    Optional<Story> optionalStory = storyDAO.decrementStoryPopularity(3);
    Assert.assertEquals(-1L, (long) optionalStory.get().getPopularity());
  }
}

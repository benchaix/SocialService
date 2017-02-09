package com.benchaix.social_service.persistence.dao.impl;

import java.util.HashMap;
import java.util.Optional;

import com.benchaix.social_service.model.Story;
import com.benchaix.social_service.persistence.dao.StoryDAO;

/**
 * Custom in memory data access object implementing StoryDAO
 * 
 * @author Ben Chaix
 */
public class InMemoryStoryDAO implements StoryDAO {
  // contains default stories
  private HashMap<Integer, Story> stories = new HashMap<>();

  public InMemoryStoryDAO() {
    initialize();
  }

  public void initialize() {
    stories = new HashMap<>();
    // add 100 default stories with no likes
    for (int i = 1; i <= 100; i++) {
      initializeStories(i);
    }
  }

  private void initializeStories(int id) {
    Story story = new Story(id, 0L);
    stories.put(id, story);
  }

  @Override
  public Optional<Story> getStoryById(int id) {
    return Optional.ofNullable(stories.get(id));
  }

  @Override
  public Optional<Story> setStoryPopularity(int id, long value) {
    Optional<Story> optionalStory = getStoryById(id);
    optionalStory.map(s -> s.setPopularity(value));
    return optionalStory;
  }

  @Override
  public Optional<Story> incrementStoryPopularity(int id) {
    Optional<Story> optionalStory = getStoryById(id);
    optionalStory.map(s -> s.incrementPopularity());
    return optionalStory;
  }

  @Override
  public Optional<Story> decrementStoryPopularity(int id) {
    Optional<Story> optionalStory = getStoryById(id);
    optionalStory.map(s -> s.decrementPopularity());
    return optionalStory;
  }
}

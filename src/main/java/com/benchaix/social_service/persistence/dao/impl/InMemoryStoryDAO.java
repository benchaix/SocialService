package com.benchaix.social_service.persistence.dao.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.benchaix.social_service.model.Story;
import com.benchaix.social_service.persistence.dao.StoryDAO;

/**
 * Custom in memory data access object implementing StoryDAO
 * 
 * @author Ben Chaix
 */
public class InMemoryStoryDAO implements StoryDAO {
  // contains default stories
  private Map<Integer, Story> stories;

  public InMemoryStoryDAO() {
    initialize();
  }

  public void initialize() {
    stories = new ConcurrentHashMap<>();
    // add 100 default stories with no likes
    for (int i = 1; i <= 100; i++) {
      addStory(i);
    }
  }

  public synchronized Story addStory(int id) {
    Story story = new Story(id, 0L);
    story = stories.putIfAbsent(id, story);
    return story;
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

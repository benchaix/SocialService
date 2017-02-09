package com.benchaix.social_service.service.impl;

import java.util.Optional;

import com.benchaix.social_service.model.Story;
import com.benchaix.social_service.persistence.dao.StoryDAO;
import com.benchaix.social_service.persistence.dao.impl.InMemoryStoryDAO;
import com.benchaix.social_service.service.StoryService;

/**
 * Custom Story service using InMemoryStoryDAO implementing StoryService
 * 
 * @author Ben Chaix
 */
public class CustomStoryService implements StoryService {

  StoryDAO storyDAO = new InMemoryStoryDAO();

  private static final String ACTION_GET = "get";
  private static final String ACTION_SET = "set";
  private static final String ACTION_INCREMENT = "increment";
  private static final String ACTION_DECREMENT = "decrement";

  private CustomStoryService() {
  }

  // Create a singleton instance
  private static class CustomStoryServiceHolder {
    private final static CustomStoryService instance = new CustomStoryService();
  }

  public static CustomStoryService getInstance() {
    return CustomStoryServiceHolder.instance;
  }

  @Override
  public void initialize() {
  }

  @Override
  public Optional<Story> getStoryById(int id) {
    return storyDAO.getStoryById(id);
  }

  @Override
  public Optional<String> getStoryJSON(int id) {
    return (performActionAndReturnJSON(ACTION_GET, id));
  }

  @Override
  public Optional<String> setStoryPopularityAndGetJSON(int id, long value) {
    return (performActionAndReturnJSON(ACTION_SET, id, value));
  }

  @Override
  public Optional<String> incrementStoryPopularityAndGetJSON(int id) {
    return (performActionAndReturnJSON(ACTION_INCREMENT, id));
  }

  @Override
  public Optional<String> decrementStoryPopularityAndGetJSON(int id) {
    return (performActionAndReturnJSON(ACTION_DECREMENT, id));
  }

  private Optional<String> performActionAndReturnJSON(String action, int id) {
    return performActionAndReturnJSON(action, id, 0L);
  }

  private Optional<String> performActionAndReturnJSON(String action, int id, long value) {

    Optional<Story> optionalStory = getStoryById(id);
    if (optionalStory.isPresent()) {
      Story story = optionalStory.get();

      // synchronization at story level to perform action and then produce JSON
      synchronized (story) {
        Optional<Story> resultStory = Optional.empty();

        if (ACTION_GET.equals(action)) {
          resultStory = storyDAO.getStoryById(id);

        } else if (ACTION_SET.equals(action)) {
          resultStory = storyDAO.setStoryPopularity(id, value);

        } else if (ACTION_INCREMENT.equals(action)) {
          resultStory = storyDAO.incrementStoryPopularity(id);

        } else if (ACTION_DECREMENT.equals(action)) {
          resultStory = storyDAO.decrementStoryPopularity(id);
        }
        return resultStory.map(s -> s.toJSONString());
      }
    }
    return Optional.empty();
  }
}

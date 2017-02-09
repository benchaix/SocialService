package com.benchaix.social_service.service;

import java.util.Optional;

import com.benchaix.social_service.model.Story;

/**
 * Story service interface to implement
 * 
 * @author Ben Chaix
 */
public interface StoryService {

  void initialize();

  Optional<Story> getStoryById(int id);

  Optional<String> getStoryJSON(int id);

  Optional<String> setStoryPopularityAndGetJSON(int id, long value);

  Optional<String> incrementStoryPopularityAndGetJSON(int id);

  Optional<String> decrementStoryPopularityAndGetJSON(int id);
}
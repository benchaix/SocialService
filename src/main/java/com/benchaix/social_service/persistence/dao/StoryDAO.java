package com.benchaix.social_service.persistence.dao;

import java.util.Optional;

import com.benchaix.social_service.model.Story;

/**
 * Story DAO interface to implement
 * 
 * @author Ben Chaix
 */
public interface StoryDAO {
  Optional<Story> getStoryById(int id);

  Optional<Story> setStoryPopularity(int id, long value);

  Optional<Story> incrementStoryPopularity(int id);

  Optional<Story> decrementStoryPopularity(int id);
}

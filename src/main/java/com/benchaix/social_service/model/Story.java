package com.benchaix.social_service.model;

import java.util.concurrent.atomic.LongAdder;

import com.benchaix.social_service.model.serializer.StoryDeserializer;
import com.benchaix.social_service.model.serializer.StorySerializer;
import com.benchaix.social_service.utils.JSONUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Story model
 * 
 * @author Ben Chaix
 */
public class Story {

  private Integer id;
  private LongAdder popularity = new LongAdder();

  public Story(Integer id, Long popularity) {
    this.id = id;
    this.popularity.reset();
    this.popularity.add(popularity);
  }

  public Integer getId() {
    return id;
  }

  public Long getPopularity() {
    return popularity.longValue();
  }

  public Long setPopularity(Long value) {
    synchronized (popularity) {
      popularity.reset();
      popularity.add(value);
      return popularity.longValue();
    }
  }

  public Long incrementPopularity() {
    popularity.increment();
    return popularity.longValue();
  }

  public Long decrementPopularity() {
    popularity.decrement();
    return popularity.longValue();
  }

  public String toJSONString() {
    Gson gson = new GsonBuilder().registerTypeAdapter(Story.class, new StorySerializer()).setPrettyPrinting().create();
    return gson.toJson(this);
  }

  public static Story fromJSONString(String jsonString) {
    if (!JSONUtils.isJSONValid(jsonString)) {
      return null;
    }

    Gson gson = new GsonBuilder().registerTypeAdapter(Story.class, new StoryDeserializer()).create();
    return gson.fromJson(jsonString, Story.class);
  }
}

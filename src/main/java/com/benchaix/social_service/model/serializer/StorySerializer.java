package com.benchaix.social_service.model.serializer;

import java.lang.reflect.Type;

import com.benchaix.social_service.model.Story;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Serialize Story into JSON
 * 
 * @author Ben Chaix
 */
public final class StorySerializer implements JsonSerializer<Story> {

  @Override
  public JsonElement serialize(Story story, Type type, JsonSerializationContext context) {
    JsonObject object = new JsonObject();
    object.addProperty("popularity", story.getPopularity());
    return object;
  }
}
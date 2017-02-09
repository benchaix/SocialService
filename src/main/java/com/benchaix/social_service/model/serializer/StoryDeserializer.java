package com.benchaix.social_service.model.serializer;

import java.lang.reflect.Type;

import com.benchaix.social_service.model.Story;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserialize Story from JSON
 * 
 * @author Ben Chaix
 */
public class StoryDeserializer implements JsonDeserializer<Story> {
  @Override
  public Story deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
    try {
      JsonObject jsonObject = json.getAsJsonObject();
      Story story = new Story(-1, jsonObject.get("popularity").getAsLong());
      return story;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}

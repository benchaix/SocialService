package com.benchaix.social_service.server.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

import com.benchaix.social_service.model.Story;

/**
 * Test concurrent setting of population
 * 
 * @author Ben Chaix
 */
public class StoryHandlerConcurrentTest2 extends StoryHandlerConfig {

  @Test
  public void shouldSucceed() throws Throwable {
    ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

    // Set the story 20 to popularity 50 a hundred times
    List<CallPostURL> tasks = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      tasks.add(new CallPostURL(BASE_URL + "/story/20", "{\"popularity\":50}"));
    }
    try {
      List<Future<String>> futures = newCachedThreadPool.invokeAll(tasks);
      for (Future<String> future : futures) {
        String result = future.get();
        // display the JSON response
        System.out.println(result);
        Story story = Story.fromJSONString(result);
        Assert.assertEquals(50L, (long) story.getPopularity());
      }

    } catch (InterruptedException | ExecutionException ex) {
      System.out.println(ex.getMessage());
    }
    newCachedThreadPool.shutdown();
  }
}

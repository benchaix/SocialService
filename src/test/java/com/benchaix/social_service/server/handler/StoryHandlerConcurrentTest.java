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
 * Test concurrent writing of population
 * 
 * @author Ben Chaix
 */
public class StoryHandlerConcurrentTest extends StoryHandlerConfig {

  @Test
  public void shouldSucceed() throws Throwable {

    // Set the story 20 to a popularity of 10
    CallPostURL postPopularity = new CallPostURL(BASE_URL + "/story/20", "{\"popularity\":10}");
    postPopularity.call();

    Thread.sleep(2000);

    ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

    // Like the story 20 a hundred times
    List<CallURL> tasks = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      tasks.add(new CallPutURL(BASE_URL + "/story/20/like", ""));
    }
    // Dislike the story 20 ten times
    for (int i = 0; i < 10; i++) {
      tasks.add(new CallPutURL(BASE_URL + "/story/20/dislike", ""));
    }
    try {
      List<Future<String>> futures = newCachedThreadPool.invokeAll(tasks);
      for (Future<String> future : futures) {
        String result = future.get();
        // display the JSON response
        System.out.println(result);
      }

      // Get the story 20 popularity
      CallGetURL getPopularity = new CallGetURL(BASE_URL + "/story/20", "");
      Story story = Story.fromJSONString(getPopularity.call());
      Assert.assertEquals(100L, (long) story.getPopularity());

    } catch (InterruptedException | ExecutionException ex) {
      System.out.println(ex.getMessage());
    }
    newCachedThreadPool.shutdown();
  }
}

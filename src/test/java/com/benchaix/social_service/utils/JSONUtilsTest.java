package com.benchaix.social_service.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * JSON validity tests
 * 
 * @author Ben Chaix
 */
public class JSONUtilsTest {

  private String jsonStringOk = "{\"popularity\": 50}";
  private String jsonStringBad = "{\"popularity: }";

  @Test
  public void whenUsingJSONOk_ThenJsonIsValid() {
    boolean result = JSONUtils.isJSONValid(jsonStringOk);
    Assert.assertTrue(result);
  }

  @Test
  public void whenUsingJSONBad_ThenJsonIsInvalid() {
    boolean result = JSONUtils.isJSONValid(jsonStringBad);
    Assert.assertFalse(result);
  }
}

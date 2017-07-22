package com.klindziuk.mob.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.klindziuk.mob.log.HarParser;
import com.klindziuk.mob.log.model.ResponseLog;

public class RewriteHTTProtocolTest {
	private final String httpVersion = "HTTP/1.1";
	private final String rewritedHttpVersion = "HTTP/1.0";
	
  @Test(dependsOnGroups={"RewriteContentFFTest.searchTestNGandRewriteToJuintInGoogle"})
  public void rewriteHttpProtocolTest() {
	 	for(ResponseLog log : HarParser.getResponses()) {
			Assert.assertNotEquals(log.getHttpVersion(), httpVersion);
			Assert.assertEquals(log.getHttpVersion(), rewritedHttpVersion);
		}
  }
}

package com.klindziuk.mob.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.klindziuk.mob.log.HarParser;
import com.klindziuk.mob.log.model.ResponseLog;

public class RewriteHTTProtocolTest {
	private final String httpVersion = "HTTP/1.1";
	private final String rewritedHttpVersion = "HTTP/1.0";
	private SoftAssert softAssert = new SoftAssert();
	
  @Test(dependsOnGroups={"RewriteContentTest.searchTestNGandRewriteToJuintInGoogle"})
  public void rewriteHttpProtocolTest() {
	    if( null == HarParser.getResponses()){
	    	Assert.fail();
	    }
	 	for(ResponseLog log : HarParser.getResponses()) {
			Assert.assertNotEquals(log.getHttpVersion(), httpVersion);
			//sometimes we get "unknown" instead of "HTTP/1.0"
			softAssert.assertEquals(log.getHttpVersion(), rewritedHttpVersion);
	}
	 	softAssert.assertAll();
  }
}

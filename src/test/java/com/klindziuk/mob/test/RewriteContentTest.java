package com.klindziuk.mob.test;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.klindziuk.mob.test.listener.HarListener;

@Listeners(HarListener.class)
public class RewriteContentTest extends BaseTest {
	
	@Test(groups = { "RewriteContentTest.searchTestNGandRewriteToJuintInGoogle" })
	public void searchTestNGandRewriteToJuintInGoogle() {
		String searchKey = "TestNG";
		String rewritedSearchKey = "jUnit";
		google.searchFor(searchKey);
		String actual = google.getSearchQueryText();
		System.out.println(actual);
		softAssert.assertNotSame(actual, searchKey);
		softAssert.assertEquals(actual, rewritedSearchKey);
		softAssert.assertAll();
	}
}

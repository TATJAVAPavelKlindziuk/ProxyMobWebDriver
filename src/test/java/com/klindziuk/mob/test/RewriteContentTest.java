package com.klindziuk.mob.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.klindziuk.mob.browser.BrowserDriver;
import com.klindziuk.mob.page.GooglePage;
import com.klindziuk.mob.server.ProxyMobServer;
import com.klindziuk.mob.test.listener.HarListener;

import net.lightbody.bmp.BrowserMobProxy;

@Listeners(HarListener.class)
public class RewriteContentTest {
	private static final String BASE_URL = "https://google.com";
	private final String searchKey = "TestNG";
	private final String rewritedSearchKey = "jUnit";
	private WebDriver driver;
	private BrowserMobProxy proxy;
	private SoftAssert softAssert;
	private GooglePage google;
	
	@BeforeSuite
	public void beforeSuit() {
		proxy = ProxyMobServer.getInstance().getBrowserMobProxy();
	}
	
	@BeforeClass
	public void beforeClass() {
		BrowserDriver.setProxy(proxy);
		driver = BrowserDriver.CHROME.selectDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(BASE_URL);
        google = PageFactory.initElements(driver, GooglePage.class);
		softAssert = new SoftAssert();
	}
	
	@Test(groups={"RewriteContentTest.searchTestNGandRewriteToJuintInGoogle"})
	public void searchTestNGandRewriteToJuintInGoogle() {
		google.searchFor(searchKey);
		String actual = google.getSearchQueryText();
		softAssert.assertNotSame(actual, searchKey);
		softAssert.assertEquals(actual, rewritedSearchKey);
		}
	
	@AfterClass
	public void afterClass() throws Exception {
		driver.quit();
		BrowserDriver.CHROME.killDriver();
	}
	
	@AfterSuite
	public void afterSuite() {
		proxy.stop();
	}
}


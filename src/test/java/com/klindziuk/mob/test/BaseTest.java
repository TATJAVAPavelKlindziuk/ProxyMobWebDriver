package com.klindziuk.mob.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.klindziuk.mob.browser.BrowserDriver;
import com.klindziuk.mob.browser.BrowserInitializer;
import com.klindziuk.mob.page.GooglePage;
import com.klindziuk.mob.server.ProxyMobServer;

import net.lightbody.bmp.BrowserMobProxy;

public class BaseTest {
	protected static final String BASE_URL = "https://google.com";
	protected BrowserMobProxy proxy;
	protected SoftAssert softAssert;
	protected GooglePage google;
	protected BrowserInitializer initializer;
	protected BrowserDriver browserDriver;
	protected WebDriver driver;

	@BeforeSuite
	public void beforeSuit() {
		proxy = ProxyMobServer.getInstance().getBrowserMobProxy();
	}

	@Parameters({ "browserName", "key", "value", "preferenceHost", "hostNumber", "preferencePort", "binaryPath" })
	@BeforeClass
	public void beforeClass(@Optional("browserName") String browserName, @Optional("key") String key,
			@Optional("value") String value, @Optional("preferenceHost") String preferenceHost,
			@Optional("hostNumber") String hostNumber, @Optional("preferencePort") String preferencePort,
			@Optional("binaryPath") String binaryPath) 
	{
		initializer = new BrowserInitializer(hostNumber, preferenceHost, preferencePort, binaryPath, key, value);
		browserDriver = new BrowserDriver(proxy, initializer);
		driver = browserDriver.selectDriver(browserName);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(BASE_URL);
		google = PageFactory.initElements(driver, GooglePage.class);
		softAssert = new SoftAssert();
	}
	
	@Parameters({ "browserName" })
	@AfterClass
	public void afterClass(@Optional("browserName") String browserName) throws Exception {
		driver.quit();
		browserDriver.killDriver(browserName);
	}

	@AfterSuite
	public void afterSuite() {
		proxy.stop();
	}
}

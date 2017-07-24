package com.klindziuk.mob.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;

import java.io.IOException;

public final class BrowserDriver {
	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private BrowserMobProxy proxy;
	private BrowserInitializer initializer;

	public BrowserDriver(BrowserMobProxy proxy, BrowserInitializer initializer) {
		this.proxy = proxy;
		this.initializer = initializer;
	}

	public WebDriver selectDriver(String browserName) {
		switch (browserName) {
		case CHROME: {
			DesiredCapabilities capabilities = initializer.getProxyChromeCapabilities(proxy);
			return new ChromeDriver(capabilities);
		}
		case FIREFOX: {
			DesiredCapabilities capabilities = initializer.getProxyFFCapabilities(proxy);
			return new FirefoxDriver(capabilities);
		}
		default: {
			return null;
		}
		}
	}
	
	public void killDriver(String browserName) {
		try {
			switch (browserName) {
			case CHROME : {
				Runtime.getRuntime().exec("taskkill /f /IM chromedriver.exe");
			}
			case FIREFOX : {
				Runtime.getRuntime().exec("taskkill /f /IM geckodriver.exe");
			}
		 }
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
}

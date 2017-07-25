package com.klindziuk.mob.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.lightbody.bmp.BrowserMobProxy;

import java.io.IOException;

public final class BrowserDriver {
	private final static InternalLogger logger = InternalLoggerFactory.getInstance("BrowserDriver");
	private static final String INVALID_BROWSER_NAME_MESSAGE = "Cannot initialize WebDriver without valid value  of browser name.";
	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private BrowserMobProxy proxy;
	private BrowserInitializer initializer;
	private DesiredCapabilities capabilities;

	public BrowserDriver(BrowserMobProxy proxy, BrowserInitializer initializer) {
		this.proxy = proxy;
		this.initializer = initializer;
	}

	public WebDriver selectDriver(String browserName) {
		if (null == browserName || browserName.isEmpty()) {
			logger.error(INVALID_BROWSER_NAME_MESSAGE);
			return null;
		}
		switch (browserName) {
		case CHROME: {
			try {
				capabilities = initializer.getProxyChromeCapabilities(proxy);
			} catch (IllegalArgumentException iaex) {
				logger.error(iaex.getMessage(), iaex);
			}
			return new ChromeDriver(capabilities);
		}
		case FIREFOX: {
			try {
				capabilities = initializer.getProxyFFCapabilities(proxy);
			} catch (IllegalArgumentException iaex) {
				logger.error(iaex.getMessage(), iaex);
			}
			return new FirefoxDriver(capabilities);
		}
		default: {
			logger.error(INVALID_BROWSER_NAME_MESSAGE);
			return null;
		}
		}
	}

	public void killDriver(String browserName) {
		if (null == browserName || browserName.isEmpty()) {
			return;
		}
		try {
			switch (browserName) {
			case CHROME: {
				Runtime.getRuntime().exec("taskkill /f /IM chromedriver.exe");
			}
			case FIREFOX: {
				Runtime.getRuntime().exec("taskkill /f /IM geckodriver.exe");
			}
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
}

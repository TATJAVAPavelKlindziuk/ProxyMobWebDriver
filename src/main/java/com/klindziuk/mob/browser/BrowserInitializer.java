package com.klindziuk.mob.browser;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;

public class BrowserInitializer {
	private static final String NULL_PROXY_MESSAGE = "Cannot initialize seleniumProxy with null BrowseMobProxy";
	private String hostNumber;
	private String preferenceHost;
	private String preferencePort;
	private String binaryPath;
	private String key;
	private String value;
	
	public BrowserInitializer(String hostNumber, String preferenceHost, String preferencePort, String binaryPath,
			String key, String value) {
		this.hostNumber = hostNumber;
		this.preferenceHost = preferenceHost;
		this.preferencePort = preferencePort;
		this.binaryPath = binaryPath;
		this.key = key;
		this.value = value;
	}

	public DesiredCapabilities getProxyChromeCapabilities(BrowserMobProxy proxy) throws IllegalArgumentException {
		if(null == proxy){
			throw new IllegalArgumentException(NULL_PROXY_MESSAGE);
		}
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--proxy-server=localhost:" + proxy.getPort());
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		System.setProperty(key,value);
		return capabilities;
	}
	
	public DesiredCapabilities getProxyFFCapabilities(BrowserMobProxy proxy) throws IllegalArgumentException {
		if(null == proxy){
			throw new IllegalArgumentException(NULL_PROXY_MESSAGE);
		}
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxOptions options = new FirefoxOptions();
		// https://github.com/mozilla/geckodriver/issues/669 - ff54;gdr18
		options.addPreference(preferenceHost, hostNumber);
		options.addPreference(preferencePort, String.valueOf(proxy.getPort()));
		capabilities.setCapability("moz:firefoxOptions", options);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setBinary(binaryPath);
		System.setProperty(key,value);
		return capabilities;
	}

}

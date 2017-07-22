package com.klindziuk.mob.browser;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;

import java.io.IOException;

public enum BrowserDriver {
	CHROME(1),
    FIREFOX(2);

    private int index;
    private static BrowserMobProxy proxy;
   
	public static void setProxy(BrowserMobProxy proxy) {
		BrowserDriver.proxy = proxy;
	}

	BrowserDriver(int browserIndex) {
        this.index = browserIndex;
    }

    public  WebDriver selectDriver() {
        switch (index) {
            case 1: {
            	Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--proxy-server=localhost:"+ proxy.getPort());
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                System.setProperty("webdriver.chrome.driver","C:/Users/Pavel_Klindziuk/Program_Files/ChromeDriver/chromedriver.exe");
                return new ChromeDriver(capabilities);
            }
            case 2: {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
                // https://github.com/mozilla/geckodriver/issues/669 - ff54;gdr18
                options.addPreference("network.proxy.http", "127.0.0.1");
                options.addPreference("network.proxy.http_port", String.valueOf(proxy.getPort()));
                capabilities.setCapability("moz:firefoxOptions", options);
                System.setProperty("webdriver.gecko.driver", "C:/Users/Pavel_Klindziuk/Program_Files/GeckoDriver18/geckodriver.exe");
            	return new FirefoxDriver(capabilities);
            }
            default: {
               return new ChromeDriver();
            }
        }
    }

    public void killDriver() {
        try {
            switch (index) {
                case 1: {
                    Runtime.getRuntime().exec("taskkill /f /IM chromedriver.exe");
                }
                case 2: {
               	 Runtime.getRuntime().exec("taskkill /f /IM geckodriver.exe");
               }
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}

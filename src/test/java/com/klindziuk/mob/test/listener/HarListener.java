package com.klindziuk.mob.test.listener;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.klindziuk.mob.log.HarParser;
import com.klindziuk.mob.server.ProxyMobServer;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;

public class HarListener implements IInvokedMethodListener {
	private BrowserMobProxy proxy;
	private Har har;
	 
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    	if (method.isTestMethod()) {
    	proxy = ProxyMobServer.getInstance().getBrowserMobProxy();
    	proxy.newHar(method.getTestMethod().getMethodName());
    	har = proxy.getHar();
       	}
    }
 
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
        	HarParser.parseRequest(har);
        	HarParser.parseResponse(har);
            }
        }
    }


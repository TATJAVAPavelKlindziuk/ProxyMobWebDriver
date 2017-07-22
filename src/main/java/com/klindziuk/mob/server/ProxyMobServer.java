package com.klindziuk.mob.server;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;

public final class ProxyMobServer {
	private static ProxyMobServer instance;
	private BrowserMobProxy proxy;

	public static ProxyMobServer getInstance() {
		if (instance == null) {
			instance = new ProxyMobServer();
		}
		return instance;
	}

	private ProxyMobServer() {
		proxy = new BrowserMobProxyServer();
		proxy.start(8787);
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		// got some troubles here
//		proxy.addRequestFilter(new MyRequestFilter());
		proxy.addResponseFilter(new MyResponseFilter());
	}

	public BrowserMobProxy getBrowserMobProxy() {
		return proxy;
	}
}

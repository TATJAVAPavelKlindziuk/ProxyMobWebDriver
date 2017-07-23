package com.klindziuk.mob.server;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;

public final class ProxyMobServer {
	@SuppressWarnings("unused")
	private static final String ANY_URL_PATTERN = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	@SuppressWarnings("unused")
	private static final String GOOGLE_URL  = "http://google.com";
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
	// for HTTPS requests BMP cannot modify the host and port, since it is always reusing a persistent connection.
	//	proxy.rewriteUrl(ANY_URL_PATTERN, GOOGLE_URL);
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		proxy.addResponseFilter(new ProxyResponseFilter());
	}

	public BrowserMobProxy getBrowserMobProxy() {
		return proxy;
	}
}

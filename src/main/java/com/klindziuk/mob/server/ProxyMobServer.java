package com.klindziuk.mob.server;

import java.io.File;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.mitm.RootCertificateGenerator;
import net.lightbody.bmp.mitm.keys.ECKeyGenerator;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import net.lightbody.bmp.proxy.CaptureType;

public final class ProxyMobServer {
	private static final String ANY_URL_PATTERN = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
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
		proxy.setTrustAllServers(true);
		proxy.setMitmManager(getMitmManager());
		proxy.start(8787);
	 // for HTTPS requests BMP cannot modify the host and port, since it is always reusing a persistent connection.
	 //	proxy.rewriteUrl(ANY_URL_PATTERN, GOOGLE_URL);
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		
		proxy.addRequestFilter(new ProxyRequestFilter());
		proxy.addResponseFilter(new ProxyResponseFilter());
	}

	public BrowserMobProxy getBrowserMobProxy() {
		return proxy;
	}
	
	private ImpersonatingMitmManager getMitmManager() {
		RootCertificateGenerator ecRootCertificateGenerator = RootCertificateGenerator.builder()
                .keyGenerator(new ECKeyGenerator())    
                .build();

        ecRootCertificateGenerator.saveRootCertificateAsPemFile(new File("my-dynamic-ca.cer"));
        ecRootCertificateGenerator.savePrivateKeyAsPemFile(new File("my-ec-private-key.pem"), "secretPassword");
        ImpersonatingMitmManager mitmManager = ImpersonatingMitmManager.builder()
                .rootCertificateSource(ecRootCertificateGenerator)
                .serverKeyGenerator(new ECKeyGenerator())
                .build();
	           
	    return mitmManager;
 	}
}

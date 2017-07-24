package com.klindziuk.mob.server;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class ProxyRequestFilter implements RequestFilter {

	@Override
	public HttpResponse filterRequest(HttpRequest arg0, HttpMessageContents arg1, HttpMessageInfo arg2) {
		
		return null;
	}

}

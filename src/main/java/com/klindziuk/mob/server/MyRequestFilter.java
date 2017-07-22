package com.klindziuk.mob.server;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class MyRequestFilter implements RequestFilter {
	private static final String GOOGLE_URL  = "https://www.google.com:443";
	
	//got some trouble here
	public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo info) {
	request.setUri(GOOGLE_URL);
	return null;
	
	}
}

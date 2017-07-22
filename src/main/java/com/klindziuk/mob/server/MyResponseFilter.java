package com.klindziuk.mob.server;


import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class MyResponseFilter implements ResponseFilter {
			
	public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo info) {
		String messageContents = contents.getTextContents();
        String newContents = messageContents.replaceAll("TestNG", "jUnit");
        contents.setTextContents(newContents);
        response.setProtocolVersion(HttpVersion.HTTP_1_0);
		}
}

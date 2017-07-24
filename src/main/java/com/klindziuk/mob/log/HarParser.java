package com.klindziuk.mob.log;

import java.util.ArrayList;
import java.util.List;

import com.klindziuk.mob.log.model.RequestLog;
import com.klindziuk.mob.log.model.ResponseLog;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarCookie;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;

public final class HarParser {
	private final static InternalLogger logger = InternalLoggerFactory.getInstance("HarParser");
	private static final String EMPTY_HAR_MESSAGE = "Har is empty, cannot perform logging.";
	private static List<RequestLog> requests;
	private static List<ResponseLog> responses;
	
	private HarParser() {
	}
	
	public static List<RequestLog> getRequests() {
		return requests;
	}

	public static List<ResponseLog> getResponses() {
		return responses;
	}

	public static void parseRequest(Har har) {
		if(0 == har.getLog().getEntries().size()){
			logger.error(EMPTY_HAR_MESSAGE);
			return;
		}
		requests = new ArrayList<>();
		for (HarEntry entry : har.getLog().getEntries()) {
		     long bodySize = entry.getRequest().getBodySize();
		     long headersSize = entry.getRequest().getHeadersSize();
			 String comment = entry.getRequest().getComment();
			 String httpVersion = entry.getRequest().getHttpVersion();
		     String method = entry.getRequest().getMethod();
		     String url = entry.getRequest().getUrl();
			 List<HarCookie> cookies = entry.getRequest().getCookies();
			 List<HarNameValuePair> headers = entry.getRequest().getHeaders();
			 List<HarNameValuePair> query = entry.getRequest().getQueryString();	 
		     RequestLog	requestLog = new RequestLog(bodySize, headersSize, comment, httpVersion, method, url, cookies, headers, query);
		     logger.info(requestLog.toString());
		     requests.add(requestLog);
		}
	}

	public static void parseResponse(Har har) {
		if(0 == har.getLog().getEntries().size()){
			logger.error(EMPTY_HAR_MESSAGE);
			return;
		}
		responses =  new ArrayList<>();
		for (HarEntry entry : har.getLog().getEntries()) {
			 int status = entry.getResponse().getStatus();
		     long bodySize = entry.getResponse().getBodySize();
		     long headersSize = entry.getResponse().getHeadersSize();
			 String comment = entry.getResponse().getComment();
			 String httpVersion = entry.getResponse().getHttpVersion();
			 String statusText = entry.getResponse().getStatusText();
			 String content = entry.getResponse().getContent().getText();
			 String redirectedUrl = entry.getResponse().getRedirectURL();
		     List<HarCookie> cookies = entry.getResponse().getCookies();
			 List<HarNameValuePair> headers = entry.getResponse().getHeaders();
			 ResponseLog responseLog = new ResponseLog(bodySize, headersSize, comment, httpVersion, cookies, headers, status, statusText, content, redirectedUrl);
			 logger.info(responseLog.toString());
			 responses.add(responseLog);
		}
	}
}

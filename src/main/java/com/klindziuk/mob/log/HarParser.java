package com.klindziuk.mob.log;

import java.util.ArrayList;
import java.util.List;

import com.klindziuk.mob.log.model.RequestLog;
import com.klindziuk.mob.log.model.ResponseLog;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

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
		if (0 == har.getLog().getEntries().size()) {
			logger.error(EMPTY_HAR_MESSAGE);
			return;
		}
		requests = new ArrayList<>();
		for (HarEntry entry : har.getLog().getEntries()) {
			RequestLog requestLog = new RequestLog();
			requestLog.setBodySize(entry.getRequest().getBodySize());
			requestLog.setHeadersSize(entry.getRequest().getHeadersSize());
			requestLog.setComment(entry.getRequest().getComment());
			requestLog.setHttpVersion(entry.getRequest().getHttpVersion());
			requestLog.setMethod(entry.getRequest().getMethod());
			requestLog.setUrl(entry.getRequest().getUrl());
			requestLog.setCookies(entry.getRequest().getCookies());
			requestLog.setHeaders(entry.getRequest().getHeaders());
			requestLog.setQueries(entry.getRequest().getQueryString());
			logger.info(requestLog.toString());
			requests.add(requestLog);
		}
	}

	public static void parseResponse(Har har) {
		if (0 == har.getLog().getEntries().size()) {
			logger.error(EMPTY_HAR_MESSAGE);
			return;
		}
		responses = new ArrayList<>();
		for (HarEntry entry : har.getLog().getEntries()) {
			ResponseLog responseLog = new ResponseLog();
			responseLog.setStatus(entry.getResponse().getStatus());
			responseLog.setBodySize(entry.getResponse().getBodySize());
			responseLog.setHeadersSize(entry.getResponse().getHeadersSize());
			responseLog.setComment(entry.getResponse().getComment());
			responseLog.setHttpVersion(entry.getResponse().getHttpVersion());
			responseLog.setStatusText(entry.getResponse().getStatusText());
			responseLog.setContent(entry.getResponse().getContent().getText());
			responseLog.setRedirectedUrl(entry.getResponse().getRedirectURL());
			responseLog.setCookies(entry.getResponse().getCookies());
			responseLog.setHeaders(entry.getResponse().getHeaders());
			logger.info(responseLog.toString());
			responses.add(responseLog);
		}
	}
}
